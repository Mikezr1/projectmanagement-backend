package com.itvitae.projectmanagement_backend.dto.user;

import com.itvitae.projectmanagement_backend.enums.UserType;
import com.itvitae.projectmanagement_backend.models.User;

public record UserUpdateDTO(
        String firstName,
        String lastName,
        String email,
        String password,
        String companyName
) {
    public void updateEntity(User user) {
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setEmail(this.email);
        user.setPassword(this.password);
        user.setCompanyName(this.companyName);
    }
}
