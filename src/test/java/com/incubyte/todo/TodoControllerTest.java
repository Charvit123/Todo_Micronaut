package com.incubyte.todo;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.micronaut.http.HttpRequest.*;
import static org.assertj.core.api.Assertions.assertThat;


@MicronautTest
public class TodoControllerTest {
    @Client("/todo")
    @Inject
    HttpClient httpClient;

    private Todo todoCreated;

    @BeforeEach
    void setUp() {
        TodoDto todoBody = new TodoDto("todo created");
        todoCreated = httpClient.toBlocking().retrieve(POST("/create", todoBody), Todo.class);
    }
    @Test
    void create_todo(){
        TodoDto todoTask = new TodoDto("new activity");
        HttpResponse<Todo> todoResponse =
                httpClient.toBlocking().exchange(POST("/create", todoTask), Todo.class);
        assertThat(todoResponse.getStatus().getCode()).isEqualTo(201);
        Todo todo = todoResponse.body();
        assertThat(todo).isNotNull();
        assertThat(todo).isInstanceOf(Todo.class);
        assertThat(todo.getId()).isNotNull();
        assertThat(todo.getTask()).isEqualTo("new activity");
    }
    @Test
    void get_all_todo(){
        HttpResponse<TodoWrapper> todoResponse = httpClient.toBlocking().exchange(GET("/find_all"),TodoWrapper.class);
        assertThat(todoResponse.getStatus().getCode()).isEqualTo(200);
        TodoWrapper todo = todoResponse.body();
        assertThat(todo).isNotNull();
        assertThat(todo).isInstanceOf(TodoWrapper.class);
        assertThat(todo.getTodos().size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    void update_todo(){
        TodoDto updatedTodo = new TodoDto("updated");
        HttpResponse<Todo> todoResponse = httpClient.toBlocking().exchange(PUT("/update/"+todoCreated.getId(),updatedTodo),Todo.class);
        Todo todo = todoResponse.body();
        assertThat(todoResponse.getStatus().getCode()).isEqualTo(200);
        assertThat(todo).isNotNull();
        assertThat(todo.getId()).isEqualTo(todoCreated.getId());
        assertThat(todo.getTask()).isEqualTo(updatedTodo.getTask());
    }
    @Test
    void delete_todo(){
        HttpResponse<Void> todoResponse = httpClient.toBlocking().exchange(DELETE("/delete/"+todoCreated.getId()), Void.class);
        assertThat(todoResponse.getStatus().getCode()).isEqualTo(200);
        assertThat(todoResponse.body()).isNull();
    }
}
