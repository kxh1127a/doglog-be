package com.example.doglogbe.exception;

public class CInvalidPasswordFormatException extends RuntimeException {

  public CInvalidPasswordFormatException() {
    super();
  }

  public CInvalidPasswordFormatException(String message) {
    super(message);
  }

  public CInvalidPasswordFormatException(String message, Throwable cause) {
    super(message, cause);
  }
}