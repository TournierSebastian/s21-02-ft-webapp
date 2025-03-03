package com.wallex.financial_platform.dtos.responses;

import java.time.LocalDateTime;
import java.util.List;

public record UserResponseDTO(
        Long id,
        String fullName,
        String dni,
        String email,
        String phoneNumber,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Boolean active
) {}