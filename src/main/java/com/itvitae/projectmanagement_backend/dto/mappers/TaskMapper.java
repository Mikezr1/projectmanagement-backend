package com.itvitae.projectmanagement_backend.dto.mappers;

import com.itvitae.projectmanagement_backend.dto.comment.CommentSummaryDTO;
import com.itvitae.projectmanagement_backend.dto.task.*;
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

    public Task toEntity(TaskCreateDTO dto, User user) {
        Task task = new Task();
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setUser(user);
        return task;
    }

    public void TaskUpdateTitleDTO(Task task, TaskUpdateTitleDTO dto) {
        task.setTitle(dto.title());
    }

    public void TaskUpdateDescriptionDTO(Task task, TaskUpdateDescriptionDTO dto) {
        task.setDescription(dto.description());
    }

    public void TaskUpdateStatusDTO(Task task, TaskUpdateStatusDTO dto) {
        task.setStatus(dto.status());
    }

    public TaskSummaryDTO toDTO(Task task) {
        List<CommentSummaryDTO> comments = task.getComments()
                .stream()
                .map(commentMapper::toDTO)
                .collect(Collectors.toList());

        return new TaskSummaryDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getCreatedAt(),
                task.getUpdatedAt(),
                comments,
                task.getStatus()
        );
    }
}
