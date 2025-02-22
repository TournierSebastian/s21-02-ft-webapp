package com.wallex.financial_platform.configs.data;

import com.wallex.financial_platform.entities.Card;
import com.wallex.financial_platform.entities.User;
import com.wallex.financial_platform.entities.enums.CardType;
import com.wallex.financial_platform.repositories.CardRepository;
import com.wallex.financial_platform.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class CardDataLoader {

    private final CardRepository cardRepository;
    private final UserRepository userRepository;

    public void load() {
        // Obtener algunos usuarios para asociar con las tarjetas
        User user1 = userRepository.findById(1L).orElseThrow();
        User user2 = userRepository.findById(2L).orElseThrow();

        // Crear las tarjetas para el usuario 1
        Card card1 = new Card(
                null,  // ID se genera automáticamente
                user1, // Relación con el usuario 1
                "encryptedNumber1234", // Número de tarjeta encriptado (ejemplo)
                CardType.CREDIT, // Tipo de tarjeta
                "Banco Nación", // Banco emisor
                "12/25", // Fecha de vencimiento
                "encryptedCvv123", // CVV encriptado (ejemplo)
                LocalDateTime.now() // Fecha de registro
        );

        Card card2 = new Card(
                null,
                user1,
                "encryptedNumber5678",
                CardType.DEBIT,
                "Banco Galicia",
                "08/24",
                "encryptedCvv567",
                LocalDateTime.now()
        );

        // Crear las tarjetas para el usuario 2
        Card card3 = new Card(
                null,
                user2,
                "encryptedNumber9876",
                CardType.CREDIT,
                "Banco Supervielle",
                "05/23",
                "encryptedCvv987",
                LocalDateTime.now()
        );

        Card card4 = new Card(
                null,
                user2,
                "encryptedNumber5432",
                CardType.DEBIT,
                "Banco Ciudad",
                "02/27",
                "encryptedCvv543",
                LocalDateTime.now()
        );

        // Guardar las tarjetas en el repositorio
        cardRepository.saveAll(List.of(card1, card2, card3, card4)); // Guardar las tarjetas
    }
}
