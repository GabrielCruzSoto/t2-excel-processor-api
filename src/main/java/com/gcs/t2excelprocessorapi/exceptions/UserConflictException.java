package com.gcs.t2excelprocessorapi.exceptions;

import org.springframework.http.HttpStatus;

public class UserConflictException extends T2Exception{
    public UserConflictException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
