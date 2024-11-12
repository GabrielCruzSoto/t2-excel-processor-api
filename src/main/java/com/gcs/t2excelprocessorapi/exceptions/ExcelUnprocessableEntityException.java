package com.gcs.t2excelprocessorapi.exceptions;

import org.springframework.http.HttpStatus;

public class ExcelUnprocessableEntityException extends T2Exception{

    public ExcelUnprocessableEntityException(String message) {
        super(message, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
