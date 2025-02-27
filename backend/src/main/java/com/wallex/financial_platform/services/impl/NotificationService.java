package com.wallex.financial_platform.services.impl;

import com.wallex.financial_platform.entities.Notification;
import com.wallex.financial_platform.entities.User;
import com.wallex.financial_platform.entities.enums.NotificationStatus;
import com.wallex.financial_platform.entities.enums.NotificationType;
import com.wallex.financial_platform.repositories.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private final JavaMailSender emailSender;


    private final NotificationRepository notificationRepository;

    public void sendEmailNotification(User user, String subject, String message) {
        try {
            // Enviar correo electrónico
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(user.getEmail());
            email.setSubject(subject);
            email.setText(message);
            emailSender.send(email);

            // Log de éxito
            logger.info("Correo enviado a: {}", user.getEmail());
        } catch (Exception e) {
            logger.error("Error enviando correo a: {} - Error: {}", user.getEmail(), e.getMessage(), e);
        }

        // Guardar notificación en la base de datos
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setMessage(message);
        notification.setType(NotificationType.EMAIL);
        notification.setStatus(NotificationStatus.SENT);
        notificationRepository.save(notification);
    }
}
