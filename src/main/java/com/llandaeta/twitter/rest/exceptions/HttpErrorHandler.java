package com.llandaeta.twitter.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.llandaeta.twitter.core.exceptions.HttpException;
import com.llandaeta.twitter.rest.dto.Error;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class HttpErrorHandler {

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<Error> handleHttpError(HttpException httpException){
        return ResponseEntity.status(httpException.getHttpStatus())
            .body(Error.builder()
                .httpCode(httpException.getHttpStatus().value())
                .message(httpException.getMessage())
                .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> unhandledExceptions(HttpException exception){
        log.error("unhandled exception", exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Error.builder()
                .httpCode(500)
                .message(exception.getMessage())
                .build());
    }

}
