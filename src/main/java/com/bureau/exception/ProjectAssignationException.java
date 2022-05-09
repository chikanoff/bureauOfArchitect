package com.bureau.exception;

import lombok.Getter;

@Getter
public class ProjectAssignationException extends RuntimeException {
    private String code;

    public ProjectAssignationException() {
    }

    public ProjectAssignationException(String message, String code) {
        super(message);
        this.code = code;
    }
}
