package com.wallex.financial_platform.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

public record LoginRequestDTO(
        @Email(message = "El email debe ser válido") String email,
        @NotBlank String password
) {}
