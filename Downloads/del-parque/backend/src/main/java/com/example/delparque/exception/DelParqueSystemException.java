package com.example.delparque.exception;

import java.sql.SQLException;

public class DelParqueSystemException extends RuntimeException {

    private final Object[] args;
    private final String code;

    public DelParqueSystemException(String message, String code, SQLException cause, Object... args) {
        super(message, cause);
        this.code = code;
        this.args = args;
    }

    public DelParqueSystemException(String message, String code, Object... args) {
        super(message);
        this.code = code;
        this.args = args;
    }

    public Object[] getArgs() {
        return args;
    }

    public String getCode() {
        return code;
    }
}
