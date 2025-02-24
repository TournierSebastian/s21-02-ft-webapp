package com.wallex.financial_platform.services.impl;

import com.wallex.financial_platform.dtos.requests.RegisterCardRequestDTO;
import com.wallex.financial_platform.dtos.requests.DniRequestDTO;
import com.wallex.financial_platform.dtos.responses.CardResponseDTO;
import com.wallex.financial_platform.entities.Card;
import com.wallex.financial_platform.entities.User;
import com.wallex.financial_platform.repositories.CardRepository;
import com.wallex.financial_platform.repositories.UserRepository;
import com.wallex.financial_platform.services.ICardService;
import com.wallex.financial_platform.services.utils.EncryptionService;
import com.wallex.financial_platform.services.utils.UserContextService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class CardService implements ICardService {

    private final CardRepository cardRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserContextService userContextService;
    private final EncryptionService encryptionService;  // Agrega un servicio de encriptación

    @Override
    public CardResponseDTO createCard(RegisterCardRequestDTO cardRequestDTO) {
        if (this.cardRepository.existsByEncryptedNumber(cardRequestDTO.encryptedNumber())) {
            throw new RuntimeException("La tarjeta ya existe en el sistema");
        }


        User user = this.userContextService.getAuthenticatedUser();

        Card card = new Card();
        card.setEncryptedNumber(encryptionService.encrypt(cardRequestDTO.encryptedNumber()));  // Usamos encriptación
        card.setType(cardRequestDTO.type());
        card.setIssuingBank(cardRequestDTO.issuingBank());
        card.setExpirationDate(cardRequestDTO.expirationDate());
        card.setEncryptedCvv(passwordEncoder.encode(cardRequestDTO.encryptedCvv()));  // Encriptar CVV
        card.setUser(user);

        this.cardRepository.save(card);

        return convertToDTO(card);
    }

    @Override
    public List<CardResponseDTO> getCardsByUserDni(DniRequestDTO dniRequestDTO) {
        return userRepository.findByDni(dniRequestDTO.dni())
                .map(user -> this.cardRepository.findByUserId(user.getId()).stream()
                        .map(card -> {
                            // Verifica si el usuario logueado es el dueño de la tarjeta
                            if (userContextService.getAuthenticatedUser().getId().equals(card.getUser().getId())) {
                                // Si el usuario es el dueño, desencripta el número de tarjeta
                                card.setEncryptedNumber(encryptionService.decrypt(card.getEncryptedNumber()));
                            }
                            return convertToDTO(card);
                        })
                        .collect(Collectors.toList())
                ).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void deleteCard(Long cardId) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card not found"));
        cardRepository.delete(card);
    }

    @Override
    public List<CardResponseDTO> getAllCards() {
        return this.cardRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private CardResponseDTO convertToDTO(Card card) {
        return new CardResponseDTO(
                card.getType(),
                card.getEncryptedNumber(),
                card.getIssuingBank(),
                card.getExpirationDate(),
                card.getRegistrationDate()
        );
    }
}
