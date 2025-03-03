package com.wallex.financial_platform.entities.listeners;

import com.wallex.financial_platform.events.NotificationEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationListener {
    private static final Logger logger = LoggerFactory.getLogger(NotificationListener.class);

    private final JavaMailSender emailSender;

    @Async
    @EventListener
    public void handleNotificationEvent(NotificationEvent event) {
        try {
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(event.getUser().getEmail());
            email.setSubject(event.getSubject());
            email.setText(event.getMessage());
            emailSender.send(email);

            logger.info("Correo enviado a: {}", event.getUser().getEmail());
        } catch (Exception e) {
            logger.error("Error enviando correo a: {} - Error: {}", event.getUser().getEmail(), e.getMessage(), e);
        }
    }
}
