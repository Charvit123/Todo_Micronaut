package com.incubyte.todo;

import com.incubyte.todo.exceptions.TodoNotFoundException;
import jakarta.inject.Singleton;

@Singleton
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo createTodoTask(TodoDto todoTask) {
        return todoRepository.save(todoTask);
    }

    public TodoWrapper findAllTask() {
        return new TodoWrapper(todoRepository.findAll());
    }

    public Todo deleteTask(int id) {
        todoRepository.getTodoById(id).orElseThrow(() -> new TodoNotFoundException("todo not found with id" + id));
        return todoRepository.delete(id);
    }

    public Todo updateTask(int id, String updatedTask) {
        todoRepository.getTodoById(id).orElseThrow(() -> new TodoNotFoundException("todo not found with id" + id));
        return todoRepository.update(id, updatedTask);
    }
}
