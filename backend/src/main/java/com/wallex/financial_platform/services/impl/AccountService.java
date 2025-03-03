package com.wallex.financial_platform.services.impl;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.wallex.financial_platform.dtos.requests.AccountRequestDTO;
import com.wallex.financial_platform.dtos.requests.CheckAccountRequestDto;
import com.wallex.financial_platform.dtos.responses.*;
import com.wallex.financial_platform.entities.Reservation;
import com.wallex.financial_platform.entities.Transaction;
import com.wallex.financial_platform.entities.User;
import com.wallex.financial_platform.entities.enums.CurrencyType;
import com.wallex.financial_platform.exceptions.AccountErrorException;
import com.wallex.financial_platform.exceptions.AccountNotFoundException;
import com.wallex.financial_platform.repositories.ReservationRepository;
import com.wallex.financial_platform.services.IAccountService;
import com.wallex.financial_platform.services.utils.AccountServiceHelper;
import com.wallex.financial_platform.services.utils.UserContextService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import com.wallex.financial_platform.entities.Account;
import com.wallex.financial_platform.repositories.AccountRepository;

import lombok.AllArgsConstructor;

@Slf4j
@Service
@AllArgsConstructor
public class AccountService implements IAccountService {
    private AccountRepository accountRepository;
    private ReservationRepository reservationRepository;
    private UserContextService userContextService;
    private AccountServiceHelper accountServiceHelper;
    private MovementService movementService;

    @SneakyThrows
    public AccountResponseDTO getAccountById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        return mapToDTO(account);
    }
    @SneakyThrows
    public List<AccountResponseDTO> getByUser() {
        User user = userContextService.getAuthenticatedUser();
        List<Account> accountList = user.getAccounts();
        return accountList.stream().map(this::mapToDTO).toList();
    }

    @Override
    public CheckAccountResponseDTO checkAccount(CheckAccountRequestDto chkAcc) {
        Optional<Account> account = accountRepository.findByCbuOrAlias(chkAcc.cbu(), chkAcc.alias());
        if (account.isPresent()) {
            return mapToCheckDTO(account.get());
        } else {
            //ES: si no existe, crear una cuenta falsa para simular que la app esta conectada al sistema bancario
            //EN: if not exists, create a fake account to simulate that the app is connected to the banking system
            Account fakeAccount = accountServiceHelper.createFakeAccount(chkAcc);
            return mapToCheckDTO(accountRepository.save(fakeAccount));
        }
    }

    @Override
    public AccountResponseDTO createAccount(AccountRequestDTO accountReq) {
        User user = this.userContextService.getAuthenticatedUser();
        Faker faker = new Faker();
        List<Account> userAccount = user.getAccounts().stream()
                .filter(account -> account.getCurrency() == accountReq.currency()).toList();
        if (!userAccount.isEmpty()) {
            throw new AccountErrorException("User already has an account with this currency");
        }
        Account newAccount = Account.builder()
                .currency(accountReq.currency())
                .cbu(faker.numerify("CBU000"+"0351"+"Ø"+"000000#######"+"Ø"))
                .alias((faker.animal().name()+"."+faker.construction().materials()+"."+faker.commerce().material()).toLowerCase())
                .user(user)
                .sourceTransactions(new ArrayList<>())
                .destinationTransactions(new ArrayList<>())
                .movements(new ArrayList<>())
                .reservations(new ArrayList<>())
                .build();
        return mapToDTO(accountRepository.save(newAccount));
    }

    @Override
    public List<TransactionResumeResponseDTO> getTransactions(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(()-> new AccountNotFoundException("Account not found"));
        List<Transaction> transactions = Stream.of(
                account.getDestinationTransactions(),
                account.getSourceTransactions())
                .flatMap(List::stream).toList();
        return transactions.stream().map(tran -> mapToDTO(tran, account)).toList();
    }

    public List<ReservationResponseDto> getReservations(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(()-> new AccountNotFoundException("Account not found"));
        List<Reservation> reservationList = account.getReservations();
        return reservationList.stream().map(this::mapToDTO).toList();
    }

    @Override
    public List<String> getCurrencies() {
        return Arrays.stream(CurrencyType.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }


    private AccountResponseDTO mapToDTO(Account account) {
        return new AccountResponseDTO(
                account.getAccountId(),
                account.getCbu(),
                account.getAlias(),
                account.getCurrency(),
                account.getAvailableBalance(),
                account.getReservedBalance()
        );
    }
    private CheckAccountResponseDTO mapToCheckDTO(Account account) {
        return new CheckAccountResponseDTO(
                account.getCbu(),
                account.getAlias(),
                account.getCurrency(),
                account.getUser().getFullName(),
                account.getUser().getDni()
        );
    }

    private TransactionResumeResponseDTO mapToDTO(Transaction transaction, Account srcAccount) {
        return new TransactionResumeResponseDTO(
                transaction.getTransactionId(),
                transaction.getSourceAccount() == srcAccount
                        ? transaction.getDestinationAccount().getUser().getFullName()
                        : transaction.getSourceAccount().getUser().getFullName(),
                transaction.getTransactionDateTime(),
                transaction.getType(),
                transaction.getStatus(),
                srcAccount == transaction.getSourceAccount()
                        ? BigDecimal.ZERO.subtract(transaction.getAmount())
                        : transaction.getAmount(),
                transaction.getReason(),
                String.format("/api/transactions/%s", transaction.getTransactionId())
        );
    }
    private ReservationResponseDto mapToDTO(Reservation reserve) {
        return new ReservationResponseDto(
                reserve.getReservationId(),
                reserve.getAccount().getAccountId(),
                reserve.getReservedAmount(),
                reserve.getCreationDate(),
                reserve.getStatus(),
                reserve.getDescription()
        );
    }
}
