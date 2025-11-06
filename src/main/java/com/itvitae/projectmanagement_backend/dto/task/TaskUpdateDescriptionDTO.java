package com.itvitae.projectmanagement_backend.dto.task;

import com.itvitae.projectmanagement_backend.models.User;

public record TaskUpdateDescriptionDTO(
        Long id,
        String description,
        User user
) {}
