package com.itvitae.projectmanagement_backend.controllers;

import com.itvitae.projectmanagement_backend.dto.task.TaskCreateDTO;
import com.itvitae.projectmanagement_backend.dto.task.TaskResponseDTO;
import com.itvitae.projectmanagement_backend.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody TaskCreateDTO taskCreateDTO) {
        TaskResponseDTO dto = taskService.createTask(taskCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTitleDescription(@PathVariable Long id, @RequestBody TaskUpdateTitleDescriptionDTO dto) {
        TaskResponseDTO response = taskService.updatetitleDescription(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<TaskResponseDTO> updateStatus(@PathVariable Long id, @RequestBody TaskUpdateStatus dto) {
        TaskResponseDTO response = taskService.updateStatus(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
