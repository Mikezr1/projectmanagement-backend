package com.itvitae.projectmanagement_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/team")
public class TeamController {
    final private TeamServices teamServices;

    @Autowired
    public TeamController(TeamServices teamServices){
        this.teamServices = teamServices;
    }
}
