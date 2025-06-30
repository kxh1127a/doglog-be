package com.example.doglogbe.exception;

public class CInvalidTokenException extends RuntimeException {

    public CInvalidTokenException() {
        super();
    }

    public CInvalidTokenException(String message) {
        super(message);
    }

    public CInvalidTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
