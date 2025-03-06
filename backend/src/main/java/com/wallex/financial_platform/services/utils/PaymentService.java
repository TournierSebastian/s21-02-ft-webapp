package com.wallex.financial_platform.services.utils;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.preference.Preference;
import com.wallex.financial_platform.dtos.requests.payment.PaymentRequestDTO;
import com.wallex.financial_platform.dtos.responses.CheckAccountResponseDTO;
import com.wallex.financial_platform.dtos.responses.TransactionResponseDTO;
import com.wallex.financial_platform.entities.Account;
import com.wallex.financial_platform.entities.Transaction;
import com.wallex.financial_platform.entities.User;
import com.wallex.financial_platform.entities.enums.TransactionStatus;
import com.wallex.financial_platform.entities.enums.TransactionType;
import com.wallex.financial_platform.exceptions.account.AccountNotFoundException;
import com.wallex.financial_platform.repositories.TransactionRepository;
import com.wallex.financial_platform.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;

@Slf4j
@Service
@AllArgsConstructor
public class PaymentService {
    private UserContextService userContextService;
    private TransactionRepository transactionRepository;
    private UserRepository userRepository;

    @SneakyThrows
    public Payment createPaymentCard(PaymentRequestDTO paymentReq) {
        MercadoPagoConfig.setAccessToken(System.getenv("MP_ACCESS_TOKEN"));
        MercadoPagoConfig.setLoggingLevel(Level.FINEST);

        PaymentClient client = new PaymentClient();
        PaymentCreateRequest createRequest =
                PaymentCreateRequest.builder()
                        .transactionAmount(paymentReq.transaction_amount())
                        .token(paymentReq.token())
                        .description(paymentReq.description())
                        .installments(paymentReq.installments())
                        .paymentMethodId(paymentReq.payment_method_id())
                        .payer(PaymentPayerRequest.builder()
                                .email(paymentReq.payer().email())
                                .build()
                        )
                        .build();
        Payment payment = null;

        try {
            payment = client.create(createRequest);
        } catch (
                MPApiException ex) {
            System.out.printf(
                    "MercadoPago Error. Status: %s, Content: %s%n",
                    ex.getApiResponse().getStatusCode(), ex.getApiResponse().getContent());
            throw new MPException(ex.getApiResponse().getContent());
        } catch (MPException ex) {
            ex.printStackTrace();
        }
        assert payment != null;
        List<Transaction> transaction = createTransaction(payment,paymentReq);
        return payment;
    }

    //funcion para crear boton de pago para redirigir a MP y asi el usuario elige cualquier medio de pago.
    @SneakyThrows
    public Preference createPaymentPreference(BigDecimal amount){
        PreferenceClient client = new PreferenceClient();

        // Create an item in the preference
        PreferenceItemRequest item =
                PreferenceItemRequest.builder()
                        .title("Ingreso por cuenta de MP")
                        .quantity(1)
                        .unitPrice(amount)
                        .build();

        MercadoPagoConfig.setAccessToken(System.getenv("MP_ACCESS_TOKEN"));

        List<PreferenceItemRequest> items = new ArrayList<>();
        items.add(item);

        PreferenceRequest request =
                PreferenceRequest.builder().items(items).purpose("wallet_purchase").build();
        Preference preference = client.create(request);
        return preference;
    }

    private List<Transaction> createTransaction(Payment payment, PaymentRequestDTO paymentReq){
        User user = userContextService.getAuthenticatedUser();
        User wallexUser = userRepository.findByDni("71221356").orElseThrow();
        Account destinationAccount = user.getAccounts().stream().filter(acc -> acc.getAccountId() == paymentReq.payer().accountId())
                .findAny().orElseThrow(()-> new AccountNotFoundException("account not found for the user"));
        Account wallexAccount = wallexUser.getAccounts().stream().filter(acc -> Objects.equals(acc.getCurrency().toString(), payment.getCurrencyId()))
                .findAny().orElseThrow(()-> new AccountNotFoundException("cuenta wallex tesoreria no encontrada"));

        // se le transfiere la totalidad del dinero al cliente contra una cuenta de wallex
        // la empresa debe tener solvencia para afrontar esta transaccion hasta que MELI le acredite el dinero a los 20 dias
        Transaction transaction = Transaction.builder()
                .sourceAccount(wallexAccount) // CBU cuenta tesoreria Wallex
                .destinationAccount(destinationAccount)
                .amount(payment.getTransactionAmount())
                .status(Objects.equals(payment.getStatus(), "approved")
                        ? TransactionStatus.COMPLETED
                        : payment.getStatus() == "rejected"
                            ? TransactionStatus.FAILED
                            : TransactionStatus.PENDING)
                .type(TransactionType.DEPOSIT)
                .reason("Ingreso de dinero por MP id# "+payment.getId())
                .build();

        User MeliUser = userRepository.findByDni("71699949").orElseThrow();
        Account meliAccount = MeliUser.getAccounts().stream().filter(acc -> Objects.equals(acc.getCurrency().toString(), payment.getCurrencyId()))
                .findAny().orElseThrow(()-> new AccountNotFoundException("cuenta wallex tesoreria no encontrada"));

        Transaction feeMP = Transaction.builder()
                .sourceAccount(destinationAccount)
                .destinationAccount(wallexAccount)
                .amount(payment.getTransactionAmount().subtract(payment.getTransactionDetails().getNetReceivedAmount()))
                .status(Objects.equals(payment.getStatus(), "approved")
                        ? TransactionStatus.COMPLETED
                        : Objects.equals(payment.getStatus(), "rejected")
                            ? TransactionStatus.FAILED
                            : TransactionStatus.PENDING)
                .type(TransactionType.TRANSFER)
                .reason("Comision MercadoPago: id#"+payment.getId())
                .build();

        Transaction cobroPendiente = Transaction.builder()
                .sourceAccount(meliAccount)
                .destinationAccount(wallexAccount)
                .amount(payment.getTransactionDetails().getNetReceivedAmount())
                .type(TransactionType.TRANSFER)
                .status(Objects.equals(payment.getStatus(), "rejected")
                        ? TransactionStatus.FAILED
                        : TransactionStatus.PENDING
                )
                .reason("MP Transaction Id: #"+payment.getId())
                .build();

        List<Transaction> saved = transactionRepository.saveAll(List.of(transaction, feeMP, cobroPendiente));
        return saved;
    }


    public TransactionResponseDTO mapToDTO(Transaction transaction) {
        return new TransactionResponseDTO(
                transaction.getTransactionId(),
                mapToDTO(transaction.getSourceAccount()),
                mapToDTO(transaction.getDestinationAccount()),
                transaction.getTransactionDateTime(),
                transaction.getType(),
                transaction.getStatus(),
                transaction.getAmount(),
                transaction.getReason()
        );
    }
    private CheckAccountResponseDTO mapToDTO(Account account) {
        return new CheckAccountResponseDTO(
                account.getCbu(),
                account.getAlias(),
                account.getCurrency(),
                account.getUser().getFullName(),
                account.getUser().getDni()
        );
    }
}
