package com.incubyte.todo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Calendar;


@Entity
@Table(name = "todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Date cannot be null")
    @Column(name = "task")
    private String task;
    @NotNull(message = "status should not be null")
    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private TodoStatus status;

    @UpdateTimestamp
    @Column(name="updatedAt")
    private Calendar updateTime;

    @CreationTimestamp
    @Column(name="createdAt")
    private Calendar createdTime;


    public Todo(int id, String task, TodoStatus status) {
        this.id = id;
        this.task = task;
        this.status = status;
    }

    public Todo() {
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

    public TodoStatus getStatus() {
        return status;
    }

    public void setStatus(TodoStatus status) {
        this.status=status;
    }

    @PreUpdate
    public void setUpdateTime() {
        this.updateTime= Calendar.getInstance();
    }

    @PrePersist
    public void setCreatedTime() {
        this.createdTime = Calendar.getInstance();
    }
}
