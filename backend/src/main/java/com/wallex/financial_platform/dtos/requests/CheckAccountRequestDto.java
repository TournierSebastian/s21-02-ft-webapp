package com.wallex.financial_platform.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public record CheckAccountRequestDto(
        @Pattern(regexp = "^[a-zA-Z]+\\.[a-zA-Z]+\\.[a-zA-Z]+$", message = "alias not valid")
        @JsonProperty(required = false)
        String alias,
        @Size(min =27, message="El Cbu debe tener al menos 27 caracteres")
        @JsonProperty(required = false)
        String cbu
) { }
