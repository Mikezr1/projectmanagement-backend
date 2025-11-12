package com.itvitae.projectmanagement_backend.dto.user;

public record UserLoginRequestDTO(
        String email,
        String password
) {
}
