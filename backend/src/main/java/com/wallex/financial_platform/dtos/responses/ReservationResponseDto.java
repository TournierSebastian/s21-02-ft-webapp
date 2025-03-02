package com.wallex.financial_platform.dtos.responses;

import com.wallex.financial_platform.entities.Account;
import com.wallex.financial_platform.entities.enums.ReservationStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ReservationResponseDto(
        Long reservationId,
        Long AccountId,
        BigDecimal reservedAmount,
        LocalDateTime creationDate,
        ReservationStatus status,
        String description
) {
}
