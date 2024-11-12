package com.gcs.t2excelprocessorapi.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class T2Exception extends RuntimeException {

    private final HttpStatus httpStatus;

    public T2Exception(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;

    }
}
