package com.gcs.t2excelprocessorapi.exceptions;

import org.springframework.http.HttpStatus;

public class AuthenticationFailedException extends T2Exception{

    public AuthenticationFailedException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
