package com.itvitae.projectmanagement_backend.models;

import jakarta.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User poster;

    private String description;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Task task;

    public Comment() {}
    public Comment(User poster, String description, Task task) {
        this.poster = poster;
        this.description = description;
        this.task = task;
    }

    public long getId() {
        return id;
    }

    public User getPoster() {
        return poster;
    }
    public void setPoster(User poster) {
        this.poster = poster;
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
