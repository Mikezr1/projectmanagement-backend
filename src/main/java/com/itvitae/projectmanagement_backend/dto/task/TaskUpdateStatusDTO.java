package com.itvitae.projectmanagement_backend.dto.task;

import com.itvitae.projectmanagement_backend.enums.Role;
import com.itvitae.projectmanagement_backend.models.User;

public record TaskUpdateStatusDTO(
        Long id,
        Role role,
        User user
) {
}
