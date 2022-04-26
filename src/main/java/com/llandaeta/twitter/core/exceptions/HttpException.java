package com.llandaeta.twitter.core.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class HttpException extends RuntimeException{
    private final HttpStatus httpStatus;

    public HttpException(HttpStatus httpStatus, String message){
        super(message);
        this.httpStatus = httpStatus != null ? httpStatus : HttpStatus.INTERNAL_SERVER_ERROR;
    }

}
