package com.wallex.financial_platform.exceptions;

public class AccountErrorException extends RuntimeException {
    public AccountErrorException(String message) {
        super(message);
    }
}
