package com.wallex.financial_platform.dtos.responses;

public record AuthResponseDTO(
        String token,
        String fullName,
        String email) {
}
