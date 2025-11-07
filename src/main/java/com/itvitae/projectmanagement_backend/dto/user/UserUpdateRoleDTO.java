package com.itvitae.projectmanagement_backend.dto.user;

import com.itvitae.projectmanagement_backend.enums.Role;
import com.itvitae.projectmanagement_backend.models.User;

public record UserUpdateRoleDTO(
        Role role
) {
    public void updateRoleEntity(User user) {
        user.setRole(this.role);
    }
}
