package com.itvitae.projectmanagement_backend.controllers;

import com.itvitae.projectmanagement_backend.dto.project.ProjectCreateDTO;
import com.itvitae.projectmanagement_backend.dto.project.ProjectDTO;
import com.itvitae.projectmanagement_backend.services.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("/project")
public class ProjectController {
    final private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@Valid @RequestBody ProjectCreateDTO createDTO) {
        ProjectDTO created = projectService.createProject(createDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> listAllProjects(){
        List<ProjectDTO> projects = projectService.findAll();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getProject(@PathVariable Long id){
        ProjectDTO project = projectService.findById(id);
        return ResponseEntity.ok(project);
    }
//
//    @PutMapping
//    //TODO stefan wijzigingen in project (project UPdate DTO)
//
//    @DeleteMapping
//    //TODO wijziging delete geheel ipv dto (response dto void?)
}
