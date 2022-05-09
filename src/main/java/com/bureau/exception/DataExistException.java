package com.bureau.exception;

import lombok.Getter;

@Getter
public class DataExistException extends RuntimeException {
    private String code;

    public DataExistException() {

    }

    public DataExistException(String message, String code) {
        super(message);
        this.code = code;
    }
}
