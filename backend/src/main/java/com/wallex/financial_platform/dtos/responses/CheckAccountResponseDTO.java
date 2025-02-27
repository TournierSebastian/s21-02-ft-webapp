package com.wallex.financial_platform.dtos.responses;

import com.wallex.financial_platform.entities.enums.CurrencyType;

public record CheckAccountResponseDTO(
        String cbu,
        String alias,
        CurrencyType currency,
        String owner,
        String dni
) {
}
