package com.itvitae.projectmanagement_backend.dto.task;

import com.itvitae.projectmanagement_backend.enums.Status;
import com.itvitae.projectmanagement_backend.models.User;

public record TaskUpdateStatusDTO(
        Long id,
        Status status,
        User user
) {
}
