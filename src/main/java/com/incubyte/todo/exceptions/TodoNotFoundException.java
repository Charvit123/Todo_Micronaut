package com.incubyte.todo.exceptions;

public class TodoNotFoundException extends TodoException{

    public TodoNotFoundException(String message) {
        super(message);
    }
}
