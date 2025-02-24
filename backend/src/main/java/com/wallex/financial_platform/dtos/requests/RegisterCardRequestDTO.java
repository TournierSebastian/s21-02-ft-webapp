package com.wallex.financial_platform.dtos.requests;

import com.wallex.financial_platform.entities.enums.CardType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RegisterCardRequestDTO(
        @NotBlank String encryptedNumber,
        @NotBlank CardType type,
        @NotBlank String issuingBank,
        @Pattern(regexp = "^\\d{2}/\\d{2}$", message = "La fecha de expiración debe seguir el formato MM/YY") String expirationDate,
        @NotBlank String encryptedCvv
) {
}
