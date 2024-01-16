package com.incubyte.todo;

import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static io.micronaut.http.HttpRequest.*;
import static org.assertj.core.api.Assertions.assertThat;


@MicronautTest
public class TodoControllerTest {
    @Client("/todo")
    @Inject
    HttpClient httpClient;
    @Test
    void create_todo(){
        TodoDto todoTask = new TodoDto("new activity");
        Todo todoResponse = httpClient.toBlocking().retrieve(POST("/create", todoTask), Todo.class);
        assertThat(todoResponse).isNotNull();
        assertThat(todoResponse).isInstanceOf(Todo.class);
        assertThat(todoResponse.getId()).isNotNull();
        assertThat(todoResponse.getTask()).isEqualTo("new activity");
    }
    @Test
    void get_all_todo(){
        TodoWrapper todoResponse = httpClient.toBlocking().retrieve(GET("/find_all"),TodoWrapper.class);
        assertThat(todoResponse).isNotNull();
        assertThat(todoResponse).isInstanceOf(TodoWrapper.class);
        assertThat(todoResponse.getTodos().size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    void update_todo(){
        TodoDto updatedTodo = new TodoDto("updated");
        Todo todoResponse = httpClient.toBlocking().retrieve(PUT("/update/100",updatedTodo),Todo.class);
        assertThat(todoResponse).isNotNull();
        assertThat(todoResponse.getId()).isEqualTo(100);
        assertThat(todoResponse.getTask()).isEqualTo(updatedTodo.getTask());
    }
    @Test
    void delete_todo(){
        Todo todoResponse = httpClient.toBlocking().retrieve(DELETE("/delete/101"),Todo.class);
        assertThat(todoResponse).isNotNull();
        assertThat(todoResponse.getId()).isEqualTo(101);
    }
}
