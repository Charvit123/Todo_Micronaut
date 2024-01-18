package com.incubyte.todo;

import io.micronaut.http.HttpResponse;
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

    @Get("/find-all")
    public HttpResponse<TodoWrapper> findAllTask() {
        return HttpResponse.ok(todoService.findAllTask());
    }

    @Get("/get-tasks")
    public HttpResponse<TodoWrapper> getTaskByPage(@QueryValue(defaultValue = "0") int pageNum, @QueryValue(defaultValue = "5") int pageSize) {
        return HttpResponse.ok(todoService.getTaskByPage(pageNum,pageSize));
    }

    @Delete(value="/delete/{id}")
    public HttpResponse<Todo> deleteTask(@PathVariable int id) {
        return HttpResponse.ok(todoService.deleteTask(id));
    }

    @Put(value="/update/{id}")
    public HttpResponse<Todo> updateTask(@PathVariable int id,@Body TodoDto updatedTodo) {
        return  HttpResponse.ok(todoService.updateTask(id,updatedTodo));
    }
}
