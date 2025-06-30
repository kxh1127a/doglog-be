package com.example.doglogbe.exception;

public class CUnauthenticatedException extends RuntimeException {

  public CUnauthenticatedException() {
    super();
  }

  public CUnauthenticatedException(String message) {
    super(message);
  }

  public CUnauthenticatedException(String message, Throwable cause) {
    super(message, cause);
  }
}
