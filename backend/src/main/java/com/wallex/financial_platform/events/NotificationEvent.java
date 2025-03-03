package com.wallex.financial_platform.events;

import com.wallex.financial_platform.entities.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class NotificationEvent extends ApplicationEvent {
private final User user;
private final String subject;
private final String message;

    public NotificationEvent(Object source, User user, String subject, String message) {
        super(source);
        this.user = user;
        this.subject = subject;
        this.message = message;
    }
}
