package com.example.doglogbe.exception;

public class CInvalidPhoneFormatException extends RuntimeException {

    public CInvalidPhoneFormatException() {
        super();
    }

    public CInvalidPhoneFormatException(String message) {
        super(message);
    }

    public CInvalidPhoneFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}