package com.itvitae.projectmanagement_backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private User poster;
    private String description;

    public Comment() {}
    public Comment(User poster, String description) {
        this.poster = poster;
        this.description = description;
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
}
