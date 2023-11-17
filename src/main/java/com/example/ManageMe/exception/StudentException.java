package com.example.ManageMe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentException extends RuntimeException{
    private static final long statusCode = 400L;
    public StudentException(String message) {
        super(message);
    }
}
