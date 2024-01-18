package com.incubyte.todo;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TodoDto(@JsonProperty("task") String task, @JsonProperty("status") TodoStatus status) {
}