package com.example.ElectronicLibrary.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends RuntimeException {
    private String errorMessage;

    private HttpStatus errorCode;

    public UserAlreadyExistsException(HttpStatus errorCode, String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }
}
