package com.itvitae.projectmanagement_backend.dto.user;

public record UserChangePasswordDTO(
        String email,
        String currentPassword,
        String newPassword
) { }
