package org.example.backend.exception;

public class ToDoNotFoundException extends Exception {

    public ToDoNotFoundException(String message) {
        super(message);
    }
}
