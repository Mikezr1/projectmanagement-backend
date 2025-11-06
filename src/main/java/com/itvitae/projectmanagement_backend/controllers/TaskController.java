package com.itvitae.projectmanagement_backend.controllers;

import com.itvitae.projectmanagement_backend.dto.task.*;
import com.itvitae.projectmanagement_backend.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PreAuthorize("hasAuthority('CREATE_TASK')")
    @PostMapping
    public ResponseEntity<TaskSummaryDTO> createTask(@RequestBody TaskCreateDTO dto) {
        TaskSummaryDTO response = taskService.createTask(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskSummaryDTO> getTask(@PathVariable Long id) {
        TaskSummaryDTO response = taskService.getTaskById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<TaskSummaryDTO>> getAllTasks() {
        List<TaskSummaryDTO> response = taskService.getAllTasks();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}/title")
    public ResponseEntity<TaskSummaryDTO> updateTitle(@PathVariable Long id, @RequestBody TaskUpdateTitleDTO dto) {
        TaskSummaryDTO response = taskService.updatetitle(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}/description")
    public ResponseEntity<TaskSummaryDTO> updateDescription(@PathVariable Long id, @RequestBody TaskUpdateDescriptionDTO dto) {
        TaskSummaryDTO response = taskService.updateDescription(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<TaskSummaryDTO> updateStatus(@PathVariable Long id, @RequestBody TaskUpdateStatusDTO dto) {
        TaskSummaryDTO response = taskService.updateStatus(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
