package com.incubyte.todo.exceptions;

public class TodoException extends RuntimeException{
    private final String message;

    public TodoException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
