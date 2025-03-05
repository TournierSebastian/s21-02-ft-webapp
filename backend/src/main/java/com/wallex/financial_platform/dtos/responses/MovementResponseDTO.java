package com.wallex.financial_platform.dtos.responses;

import java.math.BigDecimal;

public record MovementResponseDTO(
        Long movementId,
        String transactionUrl,
        UserResponseDTO user,
        String description,
        BigDecimal amount
) {
}
