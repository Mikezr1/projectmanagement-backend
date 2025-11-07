package com.itvitae.projectmanagement_backend.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {


    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true, nullable = false)


    private String projectName;

    @ManyToMany(mappedBy = "project", cascade = CascadeType.ALL)
    @JoinColumn(
            name = "team_id",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    );
    //private Team team;
    List<Team> users = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public List<Team> getUsers() {
        return users;
    }

    public void setUsers(List<Team> users) {
        this.users = users;
    }
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}

/*
stefan, laten staan aub.
deze doen we nooit, id genereerd automatisch, omdat die al gegenereerd is.
 public void setId(Long id) {
        this.id = id;
    }
 */