package com.edumunera.comercio.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorGeneralException extends RuntimeException {

    private final String code;
    private final HttpStatus httpStatus;

    public ErrorGeneralException(String msg, String code,  HttpStatus httpStatus) {
        super(msg);
        this.code = code;
        this.httpStatus = httpStatus;
    }
}
