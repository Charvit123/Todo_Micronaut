package com.incubyte.todo;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.*;

@Controller("/todo")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @Post("/create")
    public HttpResponse<Todo> createTodoTask(@Body TodoDto todoTask) {
        return HttpResponse.created(todoService.createTodoTask(todoTask));
    }

    @Get("/find_all")
    public HttpResponse<TodoWrapper> findAllTask() {
        return HttpResponse.ok(todoService.findAllTask());
    }

    @Delete(value="/delete/{id}")
    public HttpResponse<Todo> deleteTask(@PathVariable int id) {
        todoService.deleteTask(id);
        return HttpResponse.ok();
    }

    @Put(value="/update/{id}")
    public HttpResponse<Todo> updateTask(@PathVariable int id,@Body TodoDto updatedTodo) {
        return  HttpResponse.ok(todoService.updateTask(id,updatedTodo.getTask()));
    }
}
