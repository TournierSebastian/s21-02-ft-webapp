package com.wallex.financial_platform.configs.data;

import com.wallex.financial_platform.entities.Account;
import com.wallex.financial_platform.entities.Movement;
import com.wallex.financial_platform.entities.Transaction;
import com.wallex.financial_platform.repositories.AccountRepository;
import com.wallex.financial_platform.repositories.MovementRepository;
import com.wallex.financial_platform.repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class MovementDataLoader {

    private final MovementRepository movementRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public void load() {
        // Obtener algunas cuentas para asociar con los movimientos
        Account account1 = accountRepository.findById(1L).orElseThrow();
        Account account2 = accountRepository.findById(2L).orElseThrow();

        // Obtener algunas transacciones para asociar con los movimientos
        Transaction transaction1 = transactionRepository.findById(1L).orElseThrow();
        Transaction transaction2 = transactionRepository.findById(2L).orElseThrow();

        // Crear los movimientos para la cuenta 1 y la transacción 1
        Movement movement1 = new Movement(
                null, // ID se genera automáticamente
                account1, // Relación con la cuenta 1
                transaction1, // Relación con la transacción 1
                "Depósito inicial", // Descripción del movimiento
                new BigDecimal("1000.00"), // Monto del movimiento
                LocalDateTime.now() // Fecha del movimiento
        );

        Movement movement2 = new Movement(
                null,
                account1,
                transaction2,
                "Transferencia recibida",
                new BigDecimal("500.00"),
                LocalDateTime.now()
        );

        // Crear los movimientos para la cuenta 2 y la transacción 1
        Movement movement3 = new Movement(
                null,
                account2,
                transaction1,
                "Pago de factura",
                new BigDecimal("200.00"),
                LocalDateTime.now()
        );

        Movement movement4 = new Movement(
                null,
                account2,
                transaction2,
                "Retiro de efectivo",
                new BigDecimal("300.00"),
                LocalDateTime.now()
        );

        // Guardar los movimientos en el repositorio
        movementRepository.saveAll(List.of(movement1, movement2, movement3, movement4)); // Guardar los movimientos
    }
}
