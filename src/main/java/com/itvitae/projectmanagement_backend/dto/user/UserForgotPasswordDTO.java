package com.itvitae.projectmanagement_backend.dto.user;

public record UserForgotPasswordDTO(
        String email,
        String newPassword,
        String confirmPassword
) {
}
