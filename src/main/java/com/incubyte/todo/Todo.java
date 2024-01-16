package com.incubyte.todo;

public class Todo {
    private int id;



    private String task;

    public Todo() {}

    public Todo(int id, String task) {
        this.id = id;
        this.task = task;
    }

    public String getTask() {
        return task;
    }

    public int getId() {
        return id;
    }
    public void setTask(String task) {
        this.task = task;
    }
}
