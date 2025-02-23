package com.wallex.financial_platform.repositories;

import com.wallex.financial_platform.dtos.responses.CardResponseDTO;
import com.wallex.financial_platform.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByUserId(Long userId);
}
