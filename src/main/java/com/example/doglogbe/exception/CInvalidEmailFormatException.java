package com.example.doglogbe.exception;

public class CInvalidEmailFormatException extends RuntimeException {

  public CInvalidEmailFormatException() {
    super();
  }

  public CInvalidEmailFormatException(String message) {
    super(message);
  }

  public CInvalidEmailFormatException(String message, Throwable cause) {
    super(message, cause);
  }
}