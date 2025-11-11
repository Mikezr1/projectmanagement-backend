package com.itvitae.projectmanagement_backend.models;

import jakarta.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    public Comment() {}
    public Comment(User user, String description, Task task) {
        this.user = user;
        this.description = description;
        this.task = task;
    }

    public Comment(String description, User user, Task task) {
        this.description = description;
        this.user = user;
        this.task = task;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Task getTask() {
        return task;
    }
    public void setTask(Task task) {
        this.task = task;
    }
}
