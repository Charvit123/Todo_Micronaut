package com.incubyte.todo;

import com.incubyte.todo.exceptions.TodoNotFoundException;
import io.micronaut.data.model.Pageable;
import jakarta.inject.Singleton;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Singleton
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo createTodoTask(@NotNull TodoDto todoTask) {
        Todo todo = new Todo();
        todo.setTask(todoTask.task());
        todo.setStatus(todoTask.status());
        return todoRepository.save(todo);
    }

    public TodoWrapper findAllTask() {
        return new TodoWrapper(todoRepository.findAll());
    }

    public Todo deleteTask(int id) {
        Todo todo = getTodoById(id);
        todo.setStatus(TodoStatus.ARCHIVE);
        return todoRepository.update(todo);
    }

    public Todo updateTask(int id,@NotNull TodoDto updatedTask) {
        Todo todo = getTodoById(id);
        todo.setTask(updatedTask.task());
        todo.setStatus(updatedTask.status());
        return todoRepository.update(todo);
    }

    private Todo getTodoById(int id) {
        return todoRepository.findById(id).orElseThrow(() -> new TodoNotFoundException("todo not found with id" + id));
    }

    public TodoWrapper getTaskByPage(int pageNum, int pageSize) {
        List<Todo> todoPage = todoRepository.findAll(Pageable.from(pageNum, pageSize));
        return new TodoWrapper(todoPage);
    }
}
