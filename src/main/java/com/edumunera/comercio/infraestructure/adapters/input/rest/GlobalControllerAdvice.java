package com.edumunera.comercio.infraestructure.adapters.input.rest;

import com.edumunera.comercio.domain.exception.ErrorGeneralException;
import com.edumunera.comercio.infraestructure.adapters.input.rest.model.response.ErrorResponseApp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.edumunera.comercio.application.ErrorApp.INVALID_ARGUMENT;


import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(ErrorGeneralException.class)
    public ResponseEntity<ErrorResponseApp<?>> handleNotFoundException(ErrorGeneralException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(ErrorResponseApp.builder()
                .code(ex.getCode())
                .msg(ex.getMessage())
                .build());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseApp<?>> handleHttpMessageNotReadableException(HttpMessageNotReadableException  ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        log.error(ex.getMessage());
        
        return ResponseEntity.status(status).body(ErrorResponseApp.builder()
                .code(String.valueOf(status.value()))
                .msg(ex.getMessage())
                .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseApp<Map<String, String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return printErrors(ex.getBindingResult());
    }

    private static ResponseEntity<ErrorResponseApp<Map<String, String>>> printErrors(BindingResult ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getAllErrors().forEach(error -> errors.put(((FieldError) error).getField(), error.getDefaultMessage()));
        log.error(errors.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponseApp.<Map<String, String>>builder()
                .data(errors)
                .code(INVALID_ARGUMENT.getCode())
                .msg(INVALID_ARGUMENT.getMsg())
                .build());
    }
}