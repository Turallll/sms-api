package com.mit.api.error;

public class InvalidTokenException extends RuntimeException {

    private final String message;

    public InvalidTokenException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
