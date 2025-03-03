package com.wallex.financial_platform.services.impl;

import com.wallex.financial_platform.entities.Notification;
import com.wallex.financial_platform.entities.User;
import com.wallex.financial_platform.entities.enums.NotificationStatus;
import com.wallex.financial_platform.entities.enums.NotificationType;
import com.wallex.financial_platform.events.NotificationEvent;
import com.wallex.financial_platform.repositories.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final ApplicationEventPublisher eventPublisher;

    public void notifyUser(User user, String subject, String message) {
        eventPublisher.publishEvent(new NotificationEvent(this, user, subject, message));
    }
}
