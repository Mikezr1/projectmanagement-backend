package com.itvitae.projectmanagement_backend.dto.task;

import com.itvitae.projectmanagement_backend.dto.comment.CommentSummaryDTO;
import com.itvitae.projectmanagement_backend.models.User;

import java.time.LocalDateTime;
import java.util.List;

public record TaskSummaryDTO(
    long id,
    String title,
    String description,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    List<CommentSummaryDTO> comments,
    User user,
    Status status
) {}
