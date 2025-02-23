package com.wallex.financial_platform.services;

import com.wallex.financial_platform.dtos.requests.CardRequestDTO;
import com.wallex.financial_platform.dtos.requests.DniRequestDTO;
import com.wallex.financial_platform.dtos.responses.CardResponseDTO;
import com.wallex.financial_platform.entities.Card;

import java.util.List;

public interface ICardService {
    CardResponseDTO createCard(CardRequestDTO cardRequestDTO);
    void deleteCard(Card card);
    List<CardResponseDTO> getAllCards();
    List<CardResponseDTO> getCardsByUserDni(DniRequestDTO dniRequestDTO);
}
