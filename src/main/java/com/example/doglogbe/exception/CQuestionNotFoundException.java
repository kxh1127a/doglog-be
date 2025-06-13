package com.example.doglogbe.exception;

public class CQuestionNotFoundException extends RuntimeException {

    public CQuestionNotFoundException() {
        super();
    }

    public CQuestionNotFoundException(String message) {
        super(message);
    }

    public CQuestionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}