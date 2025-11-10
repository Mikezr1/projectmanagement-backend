package com.itvitae.projectmanagement_backend.dto.mappers;

import com.itvitae.projectmanagement_backend.dto.comment.CommentSummaryDTO;
import com.itvitae.projectmanagement_backend.dto.project.ProjectSummaryDTO;
import com.itvitae.projectmanagement_backend.dto.task.*;
import com.itvitae.projectmanagement_backend.dto.user.UserSummaryDTO;
import com.itvitae.projectmanagement_backend.models.Project;
import com.itvitae.projectmanagement_backend.models.Task;
import com.itvitae.projectmanagement_backend.models.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskMapper {
    private final CommentMapper commentMapper;

    public TaskMapper(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    public Task toEntity(TaskCreateDTO dto, User user, Project project) {
        Task task = new Task();
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setStatus(dto.status());
        task.setUser(user);
        task.setProject(project);
        return task;
    }

    public void updateFromDTO(Task task, TaskUpdateDTO dto) {
        if (dto.title() != null) {
            task.setTitle(dto.title());
        }
        if (dto.description() != null) {
            task.setDescription(dto.description());
        }
        if (dto.status() != null) {
            task.setStatus(dto.status());
        }
    }

    public TaskSummaryDTO toDTO(Task task) {
        List<CommentSummaryDTO> comments = task.getComments() == null
                ? List.of()
                : task.getComments().stream()
                    .map(commentMapper::toDTO)
                    .collect(Collectors.toList());

        User user = task.getUser();
        UserSummaryDTO userDTO = user == null
                ? null : new UserSummaryDTO(user.getId(), user.getFirstName(), user.getEmail());

        Project project = task.getProject();
        ProjectSummaryDTO projectDTO = project == null
                ? null : new ProjectSummaryDTO(project.getId(), project.getTitle());

        return new TaskSummaryDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getCreatedAt(),
                task.getUpdatedAt(),
                comments,
                userDTO,
                projectDTO,
                task.getStatus()
        );
    }
}
