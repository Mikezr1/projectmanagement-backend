package com.itvitae.projectmanagement_backend.dto.task;

import com.itvitae.projectmanagement_backend.enums.Status;
import com.itvitae.projectmanagement_backend.models.User;

public record TaskCreateDTO(
    String title,
    String description,
    Status status,
    Long userId,
    Long projectId
) {}
