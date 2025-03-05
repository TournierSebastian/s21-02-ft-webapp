package com.wallex.financial_platform.dtos.responses;

import com.wallex.financial_platform.entities.enums.CurrencyType;
import com.wallex.financial_platform.entities.enums.TransactionStatus;
import com.wallex.financial_platform.entities.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MovementResponseDTO(
        Long movementId,
        Long transactionId,
        CheckAccountResponseDTO sourceAccount,
        CheckAccountResponseDTO destinationAccount,
        LocalDateTime createdAt,
        String description,
        TransactionType type,
        TransactionStatus status,
        CurrencyType currency,
        BigDecimal amount
) {
}
