package com.wallex.financial_platform.repositories;

import com.wallex.financial_platform.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
