package com.example.doglogbe.exception;

public class CDuplicatePhoneException extends RuntimeException {

    public CDuplicatePhoneException() {
        super();
    }

    public CDuplicatePhoneException(String message) {
        super(message);
    }

    public CDuplicatePhoneException(String message, Throwable cause) {
        super(message, cause);
    }
}
