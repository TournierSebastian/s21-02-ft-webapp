package com.wallex.financial_platform.dtos.requests.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record Payer (
        @Email(message = "El email debe ser válido") @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "El email debe seguir el formato: ejemplo@dominio.com")
        String email,
        @JsonProperty(required = true)
        Long accountId,
        Identification identification
){}
