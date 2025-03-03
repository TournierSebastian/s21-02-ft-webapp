package com.wallex.financial_platform.services.impl;

import com.wallex.financial_platform.entities.Notification;
import com.wallex.financial_platform.entities.User;
import com.wallex.financial_platform.entities.enums.NotificationStatus;
import com.wallex.financial_platform.entities.enums.NotificationType;
import com.wallex.financial_platform.events.NotificationEvent;
import com.wallex.financial_platform.repositories.NotificationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public void notifyUser(User user, String subject, String message) {
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setType(NotificationType.EMAIL);
        notification.setMessage(message);
        notification.setStatus(NotificationStatus.PENDING);
        notification.setSendingDate(LocalDateTime.now());

        notificationRepository.save(notification);

        eventPublisher.publishEvent(new NotificationEvent(this, notification));
    }
}
