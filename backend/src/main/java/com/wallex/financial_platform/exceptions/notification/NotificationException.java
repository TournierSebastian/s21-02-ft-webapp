package com.wallex.financial_platform.exceptions.notification;

public class NotificationException extends RuntimeException{
    public NotificationException(String message, Throwable  cause) {
        super(message, cause);
    }
}
