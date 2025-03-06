package com.wallex.financial_platform.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wallex.financial_platform.entities.enums.CurrencyType;
import jakarta.validation.constraints.NotNull;

public record AccountRequestDTO(
        @NotNull(message = "El tipo de tarjeta no puede estar vacío")
        CurrencyType currency
){}
