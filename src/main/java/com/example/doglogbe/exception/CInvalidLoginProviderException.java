package com.example.doglogbe.exception;

public class CInvalidLoginProviderException extends RuntimeException {

    public CInvalidLoginProviderException() {
        super();
    }

    public CInvalidLoginProviderException(String message) {
        super(message);
    }

    public CInvalidLoginProviderException(String message, Throwable cause) {
        super(message, cause);
    }
}