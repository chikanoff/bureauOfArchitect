package com.bureau.exception;

import lombok.Getter;

@Getter
public class ItemNotFoundException extends RuntimeException {
    private String code;

    public ItemNotFoundException() {
    }

    public ItemNotFoundException(String message, String code) {
        super(message);
        this.code = code;
    }
}
