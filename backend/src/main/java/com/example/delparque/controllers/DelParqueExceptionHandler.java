package com.example.delparque.controllers;

import com.example.delparque.dto.ErrorResponse;
import com.example.delparque.exception.BadRequestDataException;
import com.example.delparque.exception.DelParqueSystemException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class DelParqueExceptionHandler {
    private final MessageSource messageSource;

    public DelParqueExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler({DelParqueSystemException.class})
    public ResponseEntity<ErrorResponse> handleBadInputException(DelParqueSystemException ex, Locale locale) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .code(ex.getCode())
                .exceptionName(ex.getClass().getCanonicalName())
                .message(messageSource.getMessage(ex.getCode(), ex.getArgs(), locale))
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({BadRequestDataException.class})
    public ResponseEntity<ErrorResponse> handleBadInputException(BadRequestDataException ex, Locale locale) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .code(ex.getCode())
                .exceptionName(ex.getClass().getCanonicalName())
                .message(messageSource.getMessage(ex.getCode(), ex.getArgs(), locale))
                .build(), HttpStatus.BAD_REQUEST);
    }

}
