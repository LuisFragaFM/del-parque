package com.example.delparque.exception;

public class BadRequestDataException extends DelParqueSystemException {

    public BadRequestDataException(String message, String code, Object... args) {
        super(message, code, args);
    }
}

