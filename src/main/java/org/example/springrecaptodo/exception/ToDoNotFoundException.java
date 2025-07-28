package org.example.springrecaptodo.exception;

public class ToDoNotFoundException extends Exception {

    public ToDoNotFoundException(String message) {
        super(message);
    }
}
