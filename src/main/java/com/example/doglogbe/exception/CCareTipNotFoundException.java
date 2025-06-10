package com.example.doglogbe.exception;

public class CCareTipNotFoundException extends RuntimeException {

    public CCareTipNotFoundException() {
        super();
    }

    public CCareTipNotFoundException(String message) {
        super(message);
    }

    public CCareTipNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
