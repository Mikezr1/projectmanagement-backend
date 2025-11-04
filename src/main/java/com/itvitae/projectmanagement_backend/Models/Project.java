package com.itvitae.projectmanagement_backend.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {

    @OneToMany(mappedBy = "project")
    List<Team> teams = new ArrayList<>();

}
