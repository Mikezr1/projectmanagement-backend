package com.itvitae.projectmanagement_backend.controllers;

import com.itvitae.projectmanagement_backend.dto.project.ProjectCreateDTO;
import com.itvitae.projectmanagement_backend.dto.project.ProjectSummaryDTO;
import com.itvitae.projectmanagement_backend.dto.project.ProjectUpdateDTO;
import com.itvitae.projectmanagement_backend.dto.task.TaskSummaryDTO;
import com.itvitae.projectmanagement_backend.dto.user.UserSummaryDTO;
import com.itvitae.projectmanagement_backend.models.User;
import com.itvitae.projectmanagement_backend.services.ProjectService;
import com.itvitae.projectmanagement_backend.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/projects")
public class ProjectController {
    final private ProjectService projectService;
    final private TaskService taskService;

    @Autowired
    public ProjectController(ProjectService projectService, TaskService taskService) {
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<ProjectSummaryDTO> createProject(@Valid @RequestBody ProjectCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.createProject(dto));
    }

    @PostMapping("/{projectId}/users")
    public ResponseEntity<ProjectSummaryDTO> addUsers(
            @PathVariable Long projectId,
            @RequestBody List<Long> userIds) {
        ProjectSummaryDTO addDTO = projectService.addUsersToProject(projectId, userIds);
        return ResponseEntity.ok(addDTO);
    }

    @DeleteMapping("/{projectId}/users")
            public ResponseEntity<ProjectSummaryDTO> deleteUsers(
            @PathVariable Long projectId,
            @RequestBody List<Long> userIds) {
                ProjectSummaryDTO updatedDTO = projectService.deleteUsersFromProject(projectId, userIds);
                return ResponseEntity.ok(updatedDTO);
    }

//    @GetMapping
//    public ResponseEntity<List<ProjectSummaryDTO>> getAllProjects(){
//        List<ProjectSummaryDTO> projects = projectService.getAllProjects();
//        return ResponseEntity.ok(projects);
//    }

    @GetMapping("/by-user")
    public ResponseEntity<List<ProjectSummaryDTO>> getProjectsByUser(@RequestParam Long userId) {
        List<ProjectSummaryDTO> projects = projectService.getProjectsByUser(userId);
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{projectId}/tasks")
    public ResponseEntity<List<TaskSummaryDTO>> getTasksByProjectId(@PathVariable Long projectId) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getTasksByProjectId(projectId));
    }

    @GetMapping("/{projectId}/members")
    public ResponseEntity<List<Long>> getUserIdsByProject(@PathVariable Long projectId) {
        List<Long> memberIds = projectService.getUserIdsByProject(projectId);
        return ResponseEntity.ok(memberIds);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectSummaryDTO> getProject(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectSummaryDTO> updateProject(@PathVariable Long id, @RequestBody ProjectUpdateDTO dto) {
        return ResponseEntity.ok(projectService.updateProject(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
