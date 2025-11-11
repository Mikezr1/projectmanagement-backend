package com.itvitae.projectmanagement_backend.dto.user;

import com.itvitae.projectmanagement_backend.enums.Role;
import com.itvitae.projectmanagement_backend.models.User;

public record UserSummaryDTO(
        Long id,
        String firstName,
        String email,
        Role role
) {}
