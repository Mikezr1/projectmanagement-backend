package com.itvitae.projectmanagement_backend.models;

import com.itvitae.projectmanagement_backend.models.Team;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true, nullable = false)

    @ManyToOne
    @JoinColumn(name = "team_id")
    //private Team team;
    List<Team> users = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<Team> getUsers() {
        return users;
    }

    public void setUsers(List<Team> users) {
        this.users = users;
    }
}

/*
stefan, laten staan aub.
deze doen we nooit, id genereerd automatisch, omdat die al gegenereerd is.
 public void setId(Long id) {
        this.id = id;
    }
 */