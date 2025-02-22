package com.wallex.financial_platform.configs.data;

import com.wallex.financial_platform.entities.Account;
import com.wallex.financial_platform.entities.User;
import com.wallex.financial_platform.entities.enums.CurrencyType;
import com.wallex.financial_platform.repositories.AccountRepository;
import com.wallex.financial_platform.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class AccountDataLoader {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public void load() {
        // Obtener algunos usuarios para asociar con las cuentas
        User user1 = userRepository.findById(1L).orElseThrow();
        User user2 = userRepository.findById(2L).orElseThrow();
        User user3 = userRepository.findById(3L).orElseThrow();
        User user4 = userRepository.findById(4L).orElseThrow();
        User user5 = userRepository.findById(5L).orElseThrow();

        // Crear las cuentas para el usuario 1
        Account account1 = new Account(
                null,  // ID se genera automáticamente
                "CBU123456789012345678901234", // CBU único
                "Alias1", // Alias único
                new BigDecimal(1000), // Saldo disponible
                new BigDecimal(200), // Saldo reservado
                CurrencyType.ARS, // Moneda
                true,  // Activa
                LocalDateTime.now(), // Fecha de creación
                LocalDateTime.now(), // Fecha de actualización
                user1, // Relación con el usuario 1
                null,  // Reservas (puedes dejarlas vacías si no tienes datos)
                null,  // Movimientos (puedes dejarlos vacíos si no tienes datos)
                null,  // Transacciones origen
                null   // Transacciones destino
        );

        Account account3 = new Account(
                null,
                "CBU111111111111111111111111",
                "Alias3",
                new BigDecimal(2500),
                new BigDecimal(500),
                CurrencyType.ARS,
                true,
                LocalDateTime.now(),
                LocalDateTime.now(),
                user2,
                null,
                null,
                null,
                null
        );

        Account account4 = new Account(
                null,
                "CBU222222222222222222222222",
                "Alias4",
                new BigDecimal(1500),
                new BigDecimal(300),
                CurrencyType.ARS,
                true,
                LocalDateTime.now(),
                LocalDateTime.now(),
                user3,
                null,
                null,
                null,
                null
        );

        // Crear las cuentas para el usuario 2
        Account account2 = new Account(
                null,
                "CBU987654321098765432109876",
                "Alias2",
                new BigDecimal(5000),
                new BigDecimal(1000),
                CurrencyType.ARS,
                true,
                LocalDateTime.now(),
                LocalDateTime.now(),
                user4,
                null,
                null,
                null,
                null
        );

        Account account5 = new Account(
                null,
                "CBU333333333333333333333333",
                "Alias5",
                new BigDecimal(3500),
                new BigDecimal(700),
                CurrencyType.ARS,
                true,
                LocalDateTime.now(),
                LocalDateTime.now(),
                user5,
                null,
                null,
                null,
                null
        );

        Account account6 = new Account(
                null,
                "CBU444444444444444444444444",
                "Alias6",
                new BigDecimal(4500),
                new BigDecimal(900),
                CurrencyType.USD,
                true,
                LocalDateTime.now(),
                LocalDateTime.now(),
                user5,
                null,
                null,
                null,
                null
        );

        // Guardar las cuentas en el repositorio
        accountRepository.saveAll(List.of(account1, account2, account3, account4, account5, account6)); // Guardar las cuentas
    }
}
