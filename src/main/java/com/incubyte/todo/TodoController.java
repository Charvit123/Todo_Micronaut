package com.incubyte.todo;

import io.micronaut.http.annotation.*;

@Controller("/todo")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @Post("/create")
    public Todo createTodoTask(@Body TodoDto todoTask) {
        return todoService.createTodoTask(todoTask);
    }

    @Get("/find_all")
    public TodoWrapper findAllTask() {
        return todoService.findAllTask();
    }

    @Delete(value="/delete/{id}")
    public Todo deleteTask(@PathVariable int id) {
        return todoService.deleteTask(id);
    }

    @Put(value="/update/{id}")
    public Todo updateTask(@PathVariable int id,@Body TodoDto updatedTodo) {
        return  todoService.updateTask(id,updatedTodo.getTask());
    }
}
