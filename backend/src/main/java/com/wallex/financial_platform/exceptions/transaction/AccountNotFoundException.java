package com.wallex.financial_platform.exceptions.transaction;

public class AccountNotFoundException extends RuntimeException {
  public AccountNotFoundException(String message) {
    super(message);
  }
}
