package com.incubyte.todo;

import jakarta.persistence.*;

@Entity
@Table(name = "todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "task")
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
