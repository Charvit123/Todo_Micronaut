package com.incubyte.todo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TodoServiceShould {
    private TodoService todoService;
    private TodoRepository todoRepository;

    @BeforeEach
    void setup() {
        todoRepository = Mockito.mock(TodoRepository.class);
        todoService = new TodoService(todoRepository);
    }

    @Test
    void create_todo_task() {
        TodoDto todoTask = new TodoDto("new activity");
        Todo currentTodo = new Todo(1, todoTask.getTask());
        when(todoRepository.save(todoTask)).thenReturn(currentTodo);
        Todo todo = todoService.createTodoTask(todoTask);
        verify(todoRepository).save(todoTask);
    }

    @Test
    void get_all_todo_task() {
        TodoWrapper todo = todoService.findAllTask();
        verify(todoRepository).findAll();
    }

    @Test
    void delete_given_task() {
        Todo todo = todoService.deleteTask(1);
        verify(todoRepository).delete(1);
    }

    @Test
    void update_given_task() {
        Todo todo = todoService.updateTask(1, "new comment");
        verify(todoRepository).update(1, "new comment");
    }

}
