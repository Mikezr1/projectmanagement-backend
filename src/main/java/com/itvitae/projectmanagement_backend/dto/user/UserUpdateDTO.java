package com.itvitae.projectmanagement_backend.dto.user;

import com.itvitae.projectmanagement_backend.enums.Role;
import com.itvitae.projectmanagement_backend.models.User;

public record UserUpdateDTO(
        Long id,
        String firstName,
        String lastName,
        Role role,
        String companyName
) {}
