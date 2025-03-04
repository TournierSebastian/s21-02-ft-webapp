package com.wallex.financial_platform.dtos.requests;

import com.wallex.financial_platform.entities.enums.TransactionType;

import java.math.BigDecimal;

public record CardTransactionRequestDTO(
        @Size(min=16, max = 16, message = "Invalid card Number") String CardNumber,
        @Size(min=27, max = 27, message = "destination Cbu not valid") String destinationCbu,
        @Min(value=1, message="amount must be more than 1 unit") BigDecimal amount,
        @NotNull TransactionType type,
        @NotNull String reason
) {
}
