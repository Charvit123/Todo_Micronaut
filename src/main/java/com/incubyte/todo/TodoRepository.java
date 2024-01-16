package com.incubyte.todo;

import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class TodoRepository {
    Todo todo1 = new Todo(100, "test todo with id 100");
    Todo todo2 = new Todo(101, "test todo with id 101");
    int currId = 0;
    List<Todo> todoList = new ArrayList<>(List.of(todo1, todo2));

    public Todo save(TodoDto todoDto) {
        currId++;
        Todo result = new Todo(currId, todoDto.getTask());
        todoList.add(result);
        return result;
    }

    public List<Todo> findAll() {
        return todoList;
    }

    public Todo delete(int id) {
        Todo deletedTode = todoList.stream().filter(todo -> todo.getId() == id).findFirst().orElseThrow(() -> new IllegalArgumentException("Id not Found"));
        todoList.remove(deletedTode);
        System.out.println(todoList);
        return deletedTode;
    }

    public Todo update(int id, String updatedTask) {
        return todoList.stream().filter(todo -> todo.getId() == id).peek((todo) -> todo.setTask(updatedTask)).findFirst().orElseThrow(() -> new IllegalArgumentException("Id not Found"));
    }
}
