package com.wallex.financial_platform.services.impl;

import com.wallex.financial_platform.dtos.requests.CardRequestDTO;
import com.wallex.financial_platform.dtos.requests.DniRequestDTO;
import com.wallex.financial_platform.dtos.responses.CardResponseDTO;
import com.wallex.financial_platform.dtos.responses.UserResponseDTO;
import com.wallex.financial_platform.entities.Card;
import com.wallex.financial_platform.entities.User;
import com.wallex.financial_platform.entities.enums.CardType;
import com.wallex.financial_platform.repositories.CardRepository;
import com.wallex.financial_platform.repositories.UserRepository;
import com.wallex.financial_platform.services.ICardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardService implements ICardService {
private final CardRepository cardRepository;
    private final UserRepository userRepository;

    @Override
    public CardResponseDTO createCard(CardRequestDTO cardRequestDTO) {
        return null;
    }

    @Override
    public void deleteCard(Card card) {

    }

    @Override
    public List<CardResponseDTO> getAllCards() {
        return this.cardRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<CardResponseDTO> getCardsByUserDni(DniRequestDTO dniRequestDTO) {
        return userRepository.findByDni(dniRequestDTO.dni())
                .map(user -> cardRepository.findByUserId(user.getId()).stream()
                        .map(this::convertToDTO)
                        .collect(Collectors.toList())
                ).orElseThrow(() -> new RuntimeException("User not found"));
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
