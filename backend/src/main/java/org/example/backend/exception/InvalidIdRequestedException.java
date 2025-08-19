package org.example.backend.exception;

public class InvalidIdRequestedException extends RuntimeException {
    public InvalidIdRequestedException(String message) {
        super(message);
    }
}
