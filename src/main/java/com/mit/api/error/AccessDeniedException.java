package com.mit.api.error;

public class AccessDeniedException extends RuntimeException {

    private final String message;

    public AccessDeniedException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
