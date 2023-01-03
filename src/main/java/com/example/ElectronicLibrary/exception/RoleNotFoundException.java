package com.example.ElectronicLibrary.exception;

import org.springframework.http.HttpStatus;

public class RoleNotFoundException extends RuntimeException {
    private String errorMessage;

    private HttpStatus errorCode;

    public RoleNotFoundException(HttpStatus errorCode, String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }
}
