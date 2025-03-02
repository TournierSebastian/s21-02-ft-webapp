package com.wallex.financial_platform.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ReservationRequestDTO(
        @NotNull Long accountId,
        @Min(value=1, message = "Minimum reservation amount is 2") BigDecimal amount,
        @JsonProperty(required = false, defaultValue = "")
        String description
) {}
