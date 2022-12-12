package com.example.ElectronicLibrary.exception;

import org.springframework.http.HttpStatus;

public class AuthException extends RuntimeException {
    private String errorMessage;

    private HttpStatus errorCode;

    public AuthException(HttpStatus errorCode, String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }
}
