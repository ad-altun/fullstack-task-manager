package org.example.fullstacktodoapp.exception;

public class InvalidIdRequestedException extends RuntimeException {
    public InvalidIdRequestedException(String message) {
        super(message);
    }
}
