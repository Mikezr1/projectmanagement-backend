package com.itvitae.projectmanagement_backend.dto.task;

import com.itvitae.projectmanagement_backend.dto.comment.CommentSummaryDTO;
import com.itvitae.projectmanagement_backend.enums.Status;

import java.time.LocalDateTime;
import java.util.List;

public record TaskSummaryDTO(
    long id,
    String title,
    String description,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    List<CommentSummaryDTO> comments,
    Status status
) {}
