package com.incubyte.todo.exceptions;

public class TodoNotFoundException extends RuntimeException{

    private final String message;

    public TodoNotFoundException(String message) {
        this.message=message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
