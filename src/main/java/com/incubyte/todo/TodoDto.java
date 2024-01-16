package com.incubyte.todo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TodoDto {
    @JsonProperty("task")
    private String task;

    public TodoDto() {}

    public TodoDto(String task) {

        this.task = task;
    }

    public String getTask() {
        return task;
    }
}
