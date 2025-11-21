package com.itvitae.projectmanagement_backend.services;

import com.itvitae.projectmanagement_backend.dto.mappers.TaskMapper;
import com.itvitae.projectmanagement_backend.dto.task.*;
import com.itvitae.projectmanagement_backend.exceptions.ProjectNotFoundException;
import com.itvitae.projectmanagement_backend.exceptions.TaskNotFoundException;
import com.itvitae.projectmanagement_backend.exceptions.UserNotFoundException;
import com.itvitae.projectmanagement_backend.models.Project;
import com.itvitae.projectmanagement_backend.models.Task;
import com.itvitae.projectmanagement_backend.models.User;
import com.itvitae.projectmanagement_backend.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;
    private final ProjectRepository projectRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository, NotificationRepository notificationRepository, ProjectRepository projectRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;
        this.projectRepository = projectRepository;
        this.taskMapper = taskMapper;
    }

    public TaskSummaryDTO createTask(TaskCreateDTO dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Project project = projectRepository.findById(dto.projectId())
                .orElseThrow(() -> new ProjectNotFoundException("Project not found"));

        Task task = taskMapper.toEntity(dto, user, project);
        taskRepository.save(task);
        return taskMapper.toDTO(task);
    }

    public List<TaskSummaryDTO> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TaskSummaryDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
        return taskMapper.toDTO(task);
    }

    public List<TaskSummaryDTO> getTasksByProjectId(Long projectId) {
        List<Task> tasks = taskRepository.findByProjectId(projectId);

        return tasks.stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TaskSummaryDTO updateTask(Long id, TaskUpdateDTO dto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));

        taskMapper.updateFromDTO(task, dto);
        taskRepository.save(task);
        return taskMapper.toDTO(task);
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
        taskRepository.delete(task);
    }



}
