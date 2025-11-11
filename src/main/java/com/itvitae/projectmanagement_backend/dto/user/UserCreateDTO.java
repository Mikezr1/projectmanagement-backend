package com.itvitae.projectmanagement_backend.dto.user;

import com.itvitae.projectmanagement_backend.enums.Role;

public record UserCreateDTO(
        String firstName,
        String lastName,
        String email,
        String password,
        Role role,
        String companyName
) {}
