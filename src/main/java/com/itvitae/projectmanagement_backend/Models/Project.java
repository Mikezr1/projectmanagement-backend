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

//    @ManyToOne
//    @JoinColumn(name = "team_id")
//    private Team team;
//    List<Team> teams = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Team getTeam() {
//        return team;
//    }
//
//    public void setTeam(Team team) {
//        this.team = team;
//    }
//
//    public List<Team> getTeams() {
//        return teams;
//    }
//
//    public void setTeams(List<Team> teams) {
//        this.teams = teams;
//    }
}
