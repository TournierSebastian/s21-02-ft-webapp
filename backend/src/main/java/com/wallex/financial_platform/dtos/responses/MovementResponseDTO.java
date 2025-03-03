package com.wallex.financial_platform.dtos.responses;

import java.math.BigDecimal;

public record MovementResponseDTO(
        Long movementId,
        String transactionUrl,
        CheckAccountResponseDTO accountOwner,
        String description,
        BigDecimal amount
) {
}
