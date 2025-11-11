package com.itvitae.projectmanagement_backend.dto.comment;

import com.itvitae.projectmanagement_backend.dto.user.UserSummaryDTO;
import com.itvitae.projectmanagement_backend.models.Task;
import com.itvitae.projectmanagement_backend.models.User;

import java.time.LocalDateTime;

public record CommentSummaryDTO(
    long id,
    String description,
    UserSummaryDTO user,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}
