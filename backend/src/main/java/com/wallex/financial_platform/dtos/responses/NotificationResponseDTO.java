package com.wallex.financial_platform.dtos.responses;

import com.wallex.financial_platform.entities.enums.NotificationStatus;
import com.wallex.financial_platform.entities.enums.NotificationType;

import java.time.LocalDateTime;

public record NotificationResponseDTO(
        LocalDateTime sendingDate,
        String message,
        NotificationStatus notificationStatus,
        NotificationType notificationType
) {
}
