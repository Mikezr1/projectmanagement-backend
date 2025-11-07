package com.itvitae.projectmanagement_backend.dto.comment;

import com.itvitae.projectmanagement_backend.models.Task;
import com.itvitae.projectmanagement_backend.models.User;

public record CommentSummaryDTO(
    long id,
    User poster,
    String description,
    Task task
) {}
