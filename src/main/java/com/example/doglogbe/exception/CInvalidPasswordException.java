package com.example.doglogbe.exception;

public class CInvalidPasswordException extends RuntimeException {

    public CInvalidPasswordException() {
        super();
    }

    public CInvalidPasswordException(String message) {
        super(message);
    }

    public CInvalidPasswordException(String message, Throwable cause) {
        super(message, cause);
    }
}
