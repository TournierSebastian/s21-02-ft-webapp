package com.wallex.financial_platform.entities.listeners;

import com.wallex.financial_platform.entities.Notification;
import com.wallex.financial_platform.entities.enums.NotificationStatus;
import com.wallex.financial_platform.events.NotificationEvent;
import com.wallex.financial_platform.exceptions.notification.NotificationException;
import com.wallex.financial_platform.repositories.NotificationRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationListener {
    private final JavaMailSender mailSender;
    private final NotificationRepository notificationRepository;

    @Async
    @EventListener
    public void handleNotificationEvent(NotificationEvent event) {
        Notification notification = event.getNotification();
        try {
            sendEmail(notification.getUser().getEmail(), notification.getMessage());
            notification.setStatus(NotificationStatus.SENT);
        } catch (NotificationException | MessagingException e) {
            notification.setStatus(NotificationStatus.FAILED);
            throw new NotificationException("No se pudo enviar el correo", e);
        }
        notificationRepository.save(notification);
    }

    private void sendEmail(String to, String text) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject("Notificación");
        helper.setText(text, true);
        mailSender.send(message);
    }
}
