package com.wallex.financial_platform.configs.data;

import com.wallex.financial_platform.entities.Card;
import com.wallex.financial_platform.entities.User;
import com.wallex.financial_platform.entities.enums.CardType;
import com.wallex.financial_platform.repositories.CardRepository;
import com.wallex.financial_platform.repositories.UserRepository;
import com.wallex.financial_platform.services.utils.EncryptionService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class CardDataLoader {

    private final CardRepository cardRepository;
    private final UserRepository userRepository;
    private final EncryptionService encryptionService;
    private final PasswordEncoder passwordEncoder;

    public void load() {
        // Obtener algunos usuarios para asociar con las tarjetas
        User user1 = userRepository.findById(1L).orElseThrow();
        User user2 = userRepository.findById(2L).orElseThrow();
        User user3 = userRepository.findById(3L).orElseThrow();
        User user4 = userRepository.findById(4L).orElseThrow();
        User user5 = userRepository.findById(5L).orElseThrow();

        // Crear las tarjetas con saldo inicial usando BigDecimal
        Card card1 = new Card(
                null,
                user1,
                encryptionService.encrypt("1234567890123456"),
                CardType.DEBIT,
                "Banco Nación",
                "12/25",
                passwordEncoder.encode("123"),
                BigDecimal.valueOf(500000.00), // Saldo inicial
                LocalDateTime.now()
        );

        Card card2 = new Card(
                null,
                user2,
                encryptionService.encrypt("9876543210987654"),
                CardType.DEBIT,
                "Banco Galicia",
                "08/24",
                passwordEncoder.encode("567"),
                BigDecimal.valueOf(1000000.00), // Saldo inicial
                LocalDateTime.now()
        );

        Card card3 = new Card(
                null,
                user3,
                encryptionService.encrypt("8765432109876543"),
                CardType.DEBIT,
                "Banco Supervielle",
                "05/23",
                passwordEncoder.encode("987"),
                BigDecimal.valueOf(750000.50), // Saldo inicial
                LocalDateTime.now()
        );

        Card card4 = new Card(
                null,
                user4,
                encryptionService.encrypt("5432109876543210"),
                CardType.DEBIT,
                "Banco Ciudad",
                "02/27",
                passwordEncoder.encode("543"),
                BigDecimal.valueOf(200000.75), // Saldo inicial
                LocalDateTime.now()
        );

        Card card5 = new Card(
                null,
                user5,
                encryptionService.encrypt("1122334455667788"),
                CardType.DEBIT,
                "Banco Santander",
                "11/26",
                passwordEncoder.encode("111"),
                BigDecimal.valueOf(120000.25), // Saldo inicial
                LocalDateTime.now()
        );

        Card card6 = new Card(
                null,
                user5,
                encryptionService.encrypt("9988776655443322"),
                CardType.CREDIT,
                "Banco Macro",
                "03/28",
                passwordEncoder.encode("222"),
                BigDecimal.valueOf(250000.00), // Saldo inicial
                LocalDateTime.now()
        );

        // Guardar las tarjetas en el repositorio
        cardRepository.saveAll(List.of(card1, card2, card3, card4, card5, card6));
    }
}
