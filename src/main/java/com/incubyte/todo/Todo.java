package com.incubyte.todo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;


@Entity
@Table(name = "todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Date cannot be null")
    @Column(name = "task")
    private String task;

    @NotNull(message = "Date cannot be null")
    @Column(name="date")
    private Date date;
    @NotNull(message = "status should not be null")
    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private TodoStatus status;


    public Todo(int id, String task, Date date , TodoStatus status) {
        this.id = id;
        this.task = task;
        this.date = date;
        this.status = status;
    }

    public Todo() {}

    public String getTask() {
        return task;
    }

    public int getId() {
        return id;
    }
    public void setTask(String task) {
        this.task = task;
    }

    public TodoStatus getStatus() {
        return status;
    }

    public void setStatus(TodoStatus status) {
        this.status=status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
