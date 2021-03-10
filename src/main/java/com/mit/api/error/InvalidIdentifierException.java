package com.mit.api.error;

public class InvalidIdentifierException extends RuntimeException {

    private final String message;

    public InvalidIdentifierException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
