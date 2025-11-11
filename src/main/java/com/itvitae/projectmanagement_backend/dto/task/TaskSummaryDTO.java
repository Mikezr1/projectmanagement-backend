package com.itvitae.projectmanagement_backend.dto.task;

import com.itvitae.projectmanagement_backend.dto.comment.CommentSummaryDTO;
import com.itvitae.projectmanagement_backend.dto.project.ProjectSummaryDTO;
import com.itvitae.projectmanagement_backend.dto.user.UserSummaryDTO;
import com.itvitae.projectmanagement_backend.enums.Role;
import com.itvitae.projectmanagement_backend.enums.Status;
import com.itvitae.projectmanagement_backend.models.User;

import java.time.LocalDateTime;
import java.util.List;

public record TaskSummaryDTO(
    Long id,
    String title,
    String description,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    List<CommentSummaryDTO> comments,
    UserSummaryDTO user,
    ProjectSummaryDTO project,
    Status status
) {}
