package com.example.doglogbe.exception;

public class CDuplicateUsernameException extends RuntimeException {

    public CDuplicateUsernameException() {
        super();
    }

    public CDuplicateUsernameException(String message) {
        super(message);
    }

    public CDuplicateUsernameException(String message, Throwable cause) {
        super(message, cause);
    }
}
