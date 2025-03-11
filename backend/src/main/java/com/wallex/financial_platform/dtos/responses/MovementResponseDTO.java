package com.wallex.financial_platform.dtos.responses;

import com.wallex.financial_platform.entities.Transaction;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public class MovementResponseDTO {
    private Long movementId;
    private Transaction transaction;
    private CheckAccountResponseDTO accountOwner;
    private LocalDateTime createdAt;
    private String description;
    private BigDecimal amount;
}
