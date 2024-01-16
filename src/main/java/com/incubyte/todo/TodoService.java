package com.incubyte.todo;

import com.incubyte.todo.exceptions.TodoNotFoundException;
import io.micronaut.http.HttpResponse;
import jakarta.inject.Singleton;

@Singleton
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo createTodoTask(TodoDto todoTask)
    {
        Todo todo = new Todo();
        todo.setTask(todoTask.getTask());
        return todoRepository.save(todo);
    }

    public TodoWrapper findAllTask() {
        return new TodoWrapper(todoRepository.findAll());
    }

    public void deleteTask(int id) {
        todoRepository.findById(id).orElseThrow(() -> new TodoNotFoundException("todo not found with id" + id));
        todoRepository.deleteById(id);
    }

    public Todo updateTask(int id, String updatedTask) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new TodoNotFoundException("todo not found with id" + id));
        todo.setTask(updatedTask);
        return todoRepository.update(todo);
    }
}
