package com.example.doglogbe.exception;

public class CInvalidUsernameFormatException extends RuntimeException {

    public CInvalidUsernameFormatException() {
        super();
    }

    public CInvalidUsernameFormatException(String message) {
        super(message);
    }

    public CInvalidUsernameFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}