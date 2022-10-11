package com.example.ElectronicLibrary.exception;

import org.springframework.http.HttpStatus;

public class BookServiceException extends RuntimeException {

    private String errorMessage;

    private HttpStatus errorCode;

    public BookServiceException(HttpStatus errorCode, String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

}
