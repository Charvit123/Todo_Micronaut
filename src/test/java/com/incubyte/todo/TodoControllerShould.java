package com.incubyte.todo;

import io.micronaut.http.HttpResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        TodoDto todoTask = new TodoDto("new activity", TodoStatus.TO_DO);
        HttpResponse<Todo> todo = todoController.createTodoTask(todoTask);
        verify(todoService).createTodoTask(todoTask);
    }

    @Test
    void get_all_task(){
        HttpResponse<TodoWrapper> todo = todoController.findAllTask();
        verify(todoService).findAllTask();
    }

    @Test
    void get_task_page(){
        int pageNum=0;
        int pageSize=5;
        HttpResponse<TodoWrapper> todo = todoController.getTaskByPage(pageNum,pageSize);
        verify(todoService).getTaskByPage(pageNum,pageSize);
    }

    @Test
    void delete_given_task(){
        HttpResponse<Todo> HttpResponse = todoController.deleteTask(1);
        verify(todoService).deleteTask(1);
    }
    @Test
    void update_given_task(){
        TodoDto updatedTodo = new TodoDto("new activity", TodoStatus.IN_PROGRESS);
        HttpResponse<Todo> todo = todoController.updateTask(1,updatedTodo);
        verify(todoService).updateTask(1,updatedTodo);
    }
}
