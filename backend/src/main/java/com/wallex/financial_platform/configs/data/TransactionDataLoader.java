package com.wallex.financial_platform.configs.data;

import com.wallex.financial_platform.entities.Account;
import com.wallex.financial_platform.entities.Transaction;
import com.wallex.financial_platform.entities.User;
import com.wallex.financial_platform.entities.enums.TransactionStatus;
import com.wallex.financial_platform.entities.enums.TransactionType;
import com.wallex.financial_platform.repositories.AccountRepository;
import com.wallex.financial_platform.repositories.TransactionRepository;
import com.wallex.financial_platform.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TransactionDataLoader {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public void load() {
        Account account1 = accountRepository.findById(1L).orElseThrow();
        Account account2 = accountRepository.findById(2L).orElseThrow();
        Account account3 = accountRepository.findById(3L).orElseThrow();
        Account account4 = accountRepository.findById(4L).orElseThrow();
        Account account5 = accountRepository.findById(5L).orElseThrow();
        Account account6 = accountRepository.findById(6L).orElseThrow();

        List<Transaction> transactions = List.of(
                new Transaction(null, account6, account1, new BigDecimal("500000.00"), TransactionType.TRANSFER, "Ingreso de dinero desde tarjeta", LocalDateTime.now(), TransactionStatus.COMPLETED),
                new Transaction(null, account6, account2, new BigDecimal("200000.00"), TransactionType.TRANSFER, "Ingreso de dinero desde tarjeta", LocalDateTime.now(), TransactionStatus.COMPLETED),
                new Transaction(null, account6, account3, new BigDecimal("200000.00"), TransactionType.TRANSFER, "Ingreso de dinero desde tarjeta", LocalDateTime.now(), TransactionStatus.COMPLETED),
                new Transaction(null, account6, account4, new BigDecimal("1500000.00"), TransactionType.TRANSFER, "Ingreso de dinero desde tarjeta", LocalDateTime.now(), TransactionStatus.COMPLETED),

                new Transaction(null, account2, account1, new BigDecimal("10000.00"), TransactionType.DEPOSIT, "Pago de servicio", LocalDateTime.now(), TransactionStatus.COMPLETED),
                new Transaction(null, account2, account3, new BigDecimal("5000.00"), TransactionType.WITHDRAWAL, "Retiro de efectivo", LocalDateTime.now(), TransactionStatus.COMPLETED),
                new Transaction(null, account3, account1, new BigDecimal("2000.00"), TransactionType.DEPOSIT, "Depósito de salario", LocalDateTime.now(), TransactionStatus.COMPLETED),
                new Transaction(null, account1, account5, new BigDecimal("150000.00"), TransactionType.TRANSFER, "Pago de alquiler", LocalDateTime.now(), TransactionStatus.COMPLETED),
                new Transaction(null, account2, account1, new BigDecimal("3000.00"), TransactionType.DEPOSIT, "Reembolso", LocalDateTime.now(), TransactionStatus.COMPLETED),
                new Transaction(null, account3, account2, new BigDecimal("7000.00"), TransactionType.WITHDRAWAL, "Retiro bancario", LocalDateTime.now(), TransactionStatus.COMPLETED),
                new Transaction(null, account1, account2, new BigDecimal("5000.00"), TransactionType.TRANSFER, "Compra en línea", LocalDateTime.now(), TransactionStatus.COMPLETED),
                new Transaction(null, account2, account4, new BigDecimal("12000.00"), TransactionType.TRANSFER, "Pago de préstamo", LocalDateTime.now(), TransactionStatus.COMPLETED),
                new Transaction(null, account3, account1, new BigDecimal("9000.00"), TransactionType.DEPOSIT, "Pago de cliente", LocalDateTime.now(), TransactionStatus.COMPLETED),
                new Transaction(null, account1, account2, new BigDecimal("2500.00"), TransactionType.TRANSFER, "Pago de factura", LocalDateTime.now(), TransactionStatus.COMPLETED),
                new Transaction(null, account2, account3, new BigDecimal("8000.00"), TransactionType.WITHDRAWAL, "Retiro de efectivo", LocalDateTime.now(), TransactionStatus.COMPLETED),
                new Transaction(null, account3, account1, new BigDecimal("10000.00"), TransactionType.DEPOSIT, "Ingreso de intereses", LocalDateTime.now(), TransactionStatus.COMPLETED),
                new Transaction(null, account1, account3, new BigDecimal("6000.00"), TransactionType.TRANSFER, "Compra de insumos", LocalDateTime.now(), TransactionStatus.COMPLETED),
                new Transaction(null, account2, account1, new BigDecimal("4500.00"), TransactionType.DEPOSIT, "Pago de amigo", LocalDateTime.now(), TransactionStatus.COMPLETED),
                new Transaction(null, account3, account2, new BigDecimal("3000.00"), TransactionType.WITHDRAWAL, "Retiro rápido", LocalDateTime.now(), TransactionStatus.COMPLETED)
        );

        transactionRepository.saveAll(transactions);
    }
}
