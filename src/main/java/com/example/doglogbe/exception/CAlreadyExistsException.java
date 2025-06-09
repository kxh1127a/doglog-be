package com.example.doglogbe.exception;

public class CAlreadyExistsException extends RuntimeException {

    public CAlreadyExistsException() {
        super();
    }

    public CAlreadyExistsException(String message) {
        super(message);
    }

    public CAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
