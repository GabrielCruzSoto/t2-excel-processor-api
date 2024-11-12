package com.gcs.t2excelprocessorapi.api.impl.advice;

import com.gcs.t2excelprocessorapi.exceptions.T2Exception;
import org.openapitools.model.ErrorRespDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApiCatchExceptionAdvice {

    @ExceptionHandler(T2Exception.class)
    public ResponseEntity<Object> handleT2Exception(T2Exception ex, WebRequest request) {

        ErrorRespDto errorRespDto = new ErrorRespDto();
        errorRespDto.setTimestamp(OffsetDateTime.from(LocalDateTime.now()));
        errorRespDto.setMessage( ex.getMessage());
        return new ResponseEntity<>(errorRespDto, ex.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(ResponseStatusException ex) {
        ErrorRespDto errorRespDto = new ErrorRespDto();
        errorRespDto.setTimestamp(OffsetDateTime.from(LocalDateTime.now()));
        errorRespDto.setMessage( ex.getMessage());
        return new ResponseEntity<>(errorRespDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
