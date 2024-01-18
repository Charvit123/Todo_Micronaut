package com.incubyte.todo;

import com.incubyte.todo.exceptions.TodoNotFoundException;
import io.micronaut.data.model.Pageable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TodoServiceShould {
    private TodoService todoService;
    private TodoRepository todoRepository;
    private Todo todo1;
    private Todo todo2;
    private Todo todo3;
    private Todo todo4;
    private Todo todo5;
    private Todo todo6;

    @BeforeEach
    void setup() {
        todo1 = new Todo(1, "todo1", new Date(), TodoStatus.TO_DO);
        todo2 = new Todo(2, "todo2", new Date(), TodoStatus.IN_PROGRESS);
        todo3 = new Todo(1, "todo1", new Date(), TodoStatus.TO_DO);
        todo4 = new Todo(1, "todo1", new Date(), TodoStatus.TO_DO);
        todo5 = new Todo(1, "todo1", new Date(), TodoStatus.TO_DO);
        todo6 = new Todo(1, "todo1", new Date(), TodoStatus.TO_DO);
        todoRepository = Mockito.mock(TodoRepository.class);
        todoService = new TodoService(todoRepository);
    }

    @Test
    void create_todo_task() {
        TodoDto todoTask = new TodoDto("new activity", TodoStatus.TO_DO);
        Todo currentTodo = new Todo(1, todoTask.task(), new Date() , todoTask.status());
        when(todoRepository.save(Mockito.any())).thenReturn(currentTodo);
        Todo todo = todoService.createTodoTask(todoTask);
        verify(todoRepository).save(Mockito.any());
        assertThat(todo).isEqualTo(currentTodo);
    }
    @Test
    void create_todo_task_not_null() {
        assertThrows(NullPointerException.class, () -> {
            Todo todo = todoService.createTodoTask(null);
        });
    }

    @Test
    void update_todo_task_not_null() {
        when(todoRepository.findById(todo1.getId())).thenReturn(Optional.ofNullable(todo1));
        assertThrows(NullPointerException.class, () -> {
            Todo todo = todoService.updateTask(todo1.getId(),null);
        });
    }

    @Test
    void get_all_todo_task() {
        List<Todo> expectedAllTodos = new ArrayList<Todo>(List.of(todo1, todo2, todo3, todo4, todo5,todo6));
        when(todoRepository.findAll()).thenReturn(expectedAllTodos);
        TodoWrapper todos = todoService.findAllTask();
        verify(todoRepository).findAll();
        assertThat(todos.getTodos()).isEqualTo(expectedAllTodos);
    }

    @Test
    void get_todo_task_page() {
        int pageNum=0;
        int pageSize=5;
        List<Todo> expectedAllTodos = new ArrayList<Todo>(List.of(todo1, todo2, todo3, todo4, todo5));
        when(todoRepository.findAll(Pageable.from(pageNum,pageSize))).thenReturn(expectedAllTodos);
        TodoWrapper todos = todoService.getTaskByPage(pageNum,pageSize);
        verify(todoRepository).findAll(Pageable.from(pageNum,pageSize));
        assertThat(todos.getTodos()).isEqualTo(expectedAllTodos);
    }

    @Test
    void delete_given_task() {
        int id = todo1.getId();
        TodoStatus resoposeStatus = TodoStatus.ARCHIVE;
        when(todoRepository.findById(id)).thenReturn(Optional.ofNullable(todo1));
        Todo expectedTodo = new Todo(id, todo1.getTask(), new Date() , resoposeStatus);
        when(todoRepository.update(Mockito.any())).thenReturn(expectedTodo);
        Todo todo=todoService.deleteTask(id);
        verify(todoRepository).findById(id);
        verify(todoRepository).update(Mockito.any());
        assertThat(todo).isEqualTo(expectedTodo);
    }

    @Test
    void update_given_task() {
        int id = todo1.getId();
        String updatedTask = "updated todo";
        TodoStatus updatedStatus = TodoStatus.DONE;
        when(todoRepository.findById(id)).thenReturn(Optional.ofNullable(todo1));
        Todo expectedTodo = new Todo(id, updatedTask, new Date() , updatedStatus);
        when(todoRepository.update(Mockito.any())).thenReturn(expectedTodo);
        Todo updatedTodo = todoService.updateTask(id,new TodoDto(updatedTask,updatedStatus));
        verify(todoRepository).findById(id);
        verify(todoRepository).update(Mockito.any());
        assertThat(updatedTodo).isEqualTo(expectedTodo);
    }
    @Test
    void throw_exception_if_todo_not_exist_on_update(){
        when(todoRepository.findById(11)).thenReturn(Optional.ofNullable(null));
        assertThatThrownBy(() -> todoService.updateTask(11,new TodoDto("some task",TodoStatus.IN_PROGRESS)))
                .isInstanceOf(TodoNotFoundException.class)
                .hasMessage("todo not found with id" + 11);
        ;
    }

}
