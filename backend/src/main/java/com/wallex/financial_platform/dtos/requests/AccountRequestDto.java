package com.wallex.financial_platform.dtos.requests;

import com.wallex.financial_platform.entities.enums.CurrencyType;
import jakarta.validation.constraints.NotBlank;

public record AccountRequestDto(
        @NotBlank CurrencyType currency
) {
}
