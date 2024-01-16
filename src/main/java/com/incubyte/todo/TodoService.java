package com.incubyte.todo;

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
        return todoRepository.delete(id);
    }

    public Todo updateTask(int id, String updatedTask) {
        return todoRepository.update(id, updatedTask);
    }
}
