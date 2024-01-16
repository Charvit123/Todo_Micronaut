package com.incubyte.todo;

import com.incubyte.todo.exceptions.TodoNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TodoServiceShould {
    private TodoService todoService;
    private TodoRepository todoRepository;
    private Todo todo1;
    private Todo todo2;

    @BeforeEach
    void setup() {
        todo1 = new Todo(1, "todo1");
        todo2 = new Todo(2, "todo2");
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
        assertThat(todo).isEqualTo(currentTodo);
    }

    @Test
    void get_all_todo_task() {
        List<Todo> expectedAllTodos = new ArrayList<Todo>(List.of(todo1, todo2));
        when(todoRepository.findAll()).thenReturn(expectedAllTodos);
        TodoWrapper todos = todoService.findAllTask();
        verify(todoRepository).findAll();
        assertThat(todos.getTodos()).isEqualTo(expectedAllTodos);
    }

    @Test
    void delete_given_task() {
        Integer id = todo1.getId();
        String todoTask = "deleted todo";
        when(todoRepository.getTodoById(id)).thenReturn(Optional.ofNullable(todo1));
        Todo expectedTodo = new Todo(id, todoTask);
        when(todoRepository.delete(id)).thenReturn(expectedTodo);
        Todo deletedTodo = todoService.deleteTask(id);
        verify(todoRepository).getTodoById(id);
        verify(todoRepository).delete(id);
        assertThat(deletedTodo).isEqualTo(expectedTodo);
    }

    @Test
    void update_given_task() {
        Integer id = todo1.getId();
        String updated = "updated todo";
        when(todoRepository.getTodoById(id)).thenReturn(Optional.ofNullable(todo1));
        Todo expectedTodo = new Todo(id, updated);
        when(todoRepository.update(id,updated)).thenReturn(expectedTodo);
        Todo updatedTodo = todoService.updateTask(id,updated);
        verify(todoRepository).getTodoById(id);
        verify(todoRepository).update(id,updated);
        assertThat(updatedTodo).isEqualTo(expectedTodo);
    }

    @Test
    void throw_exception_if_todo_not_exist_on_delete(){
        when(todoRepository.getTodoById(11)).thenReturn(Optional.ofNullable(null));
        assertThatThrownBy(() -> todoService.deleteTask(11)).isInstanceOf(TodoNotFoundException.class);
    }
    @Test
    void throw_exception_if_todo_not_exist_on_update(){
        when(todoRepository.getTodoById(11)).thenReturn(Optional.ofNullable(null));
        assertThatThrownBy(() -> todoService.deleteTask(11)).isInstanceOf(TodoNotFoundException.class);
    }

}
