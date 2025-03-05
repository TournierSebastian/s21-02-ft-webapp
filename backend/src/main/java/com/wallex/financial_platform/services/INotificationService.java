package com.wallex.financial_platform.services;

import com.wallex.financial_platform.dtos.responses.NotificationResponseDTO;

import java.util.List;

public interface INotificationService {
    List<NotificationResponseDTO> getAllNotificationByUserOnline();
}
