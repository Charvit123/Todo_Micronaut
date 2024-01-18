package com.incubyte.todo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TodoWrapper {
    private List<Todo> todos;

    @JsonCreator
    public TodoWrapper(@JsonProperty("todos") List<Todo> todos) {
        this.todos = todos;
    }

    public List<Todo> getTodos() {
        return todos;
    }
}
