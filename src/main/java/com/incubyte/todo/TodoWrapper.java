package com.incubyte.todo;

import java.util.List;

public class TodoWrapper {
    private List<Todo> todos;

    public TodoWrapper() {}

    public TodoWrapper(List<Todo> todos) {
        this.todos = todos;
    }

    public List<Todo> getTodos() {
        return todos;
    }
}
