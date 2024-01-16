package com.incubyte.todo;

import io.micronaut.http.HttpResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TodoControllerShould {
    private TodoService todoService;
    private TodoController todoController;

    @BeforeEach
    void setup(){
        todoService = mock(TodoService.class);
        todoController=new TodoController(todoService);
    }

    @Test
    void create_task(){
        TodoDto todoTask = new TodoDto("new activity");
        HttpResponse<Todo> todo = todoController.createTodoTask(todoTask);
        verify(todoService).createTodoTask(todoTask);
    }

    @Test
    void get_all_task(){
        HttpResponse<TodoWrapper> todo = todoController.findAllTask();
        verify(todoService).findAllTask();
    }

    @Test
    void delete_given_task(){
        todoController.deleteTask(1);
        verify(todoService).deleteTask(1);
    }
    @Test
    void update_given_task(){
        TodoDto updatedTodo = new TodoDto("new activity");
        HttpResponse<Todo> todo = todoController.updateTask(1,updatedTodo);
        verify(todoService).updateTask(1,updatedTodo.getTask());
    }
}
