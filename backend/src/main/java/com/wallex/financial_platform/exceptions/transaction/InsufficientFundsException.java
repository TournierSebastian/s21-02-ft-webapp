package com.wallex.financial_platform.exceptions.transaction;

public class InsufficientFundsException extends RuntimeException {
  public InsufficientFundsException(String message) {
    super(message);
  }
}
