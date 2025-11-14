package com.itvitae.projectmanagement_backend.dto.user;

import com.itvitae.projectmanagement_backend.models.User;

public record UserUpdatePasswordDTO(
        String password
) {
    public void updatePasswordEntity(User user) {
        user.setPassword(this.password);
    }
}
