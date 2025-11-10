package com.itvitae.projectmanagement_backend.services;

import com.itvitae.projectmanagement_backend.dto.mappers.TaskMapper;
import com.itvitae.projectmanagement_backend.dto.task.*;
import com.itvitae.projectmanagement_backend.exceptions.TaskNotFoundException;
import com.itvitae.projectmanagement_backend.exceptions.UserNotFoundException;
import com.itvitae.projectmanagement_backend.models.Task;
import com.itvitae.projectmanagement_backend.models.User;
import com.itvitae.projectmanagement_backend.repositories.CommentRepository;
import com.itvitae.projectmanagement_backend.repositories.NotificationRepository;
import com.itvitae.projectmanagement_backend.repositories.TaskRepository;
import com.itvitae.projectmanagement_backend.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, CommentRepository commentRepository, UserRepository userRepository, NotificationRepository notificationRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;
        this.taskMapper = taskMapper;
    }

    @Transactional
    public TaskSummaryDTO createTask(TaskCreateDTO dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Task task = taskMapper.toEntity(dto, user);
        taskRepository.save(task);

//        Notification notification = new Notification(
//                "Task created: " + task.getTitle(),
//                user
//        );

//        notificationRepository.save(notification);
        return taskMapper.toDTO(task);
    }

    @Transactional
    public List<TaskSummaryDTO> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();

        return tasks.stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public TaskSummaryDTO updateTask(Long id, TaskUpdateDTO dto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));

        taskMapper.updateFromDTO(task, dto);
        taskRepository.save(task);
        return taskMapper.toDTO(task);
    }

    @Transactional
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));

//        Notification notification = new Notification(
//                "Task deleted: " + task.getTitle()
//        );
//        notificationRepository.save(notification);

        taskRepository.delete(task);
    }

    @Transactional
    public TaskSummaryDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));

        return taskMapper.toDTO(task);
    }
}
