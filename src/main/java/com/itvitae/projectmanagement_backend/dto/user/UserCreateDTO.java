package com.itvitae.projectmanagement_backend.dto.user;

import com.itvitae.projectmanagement_backend.models.User;

public record UserCreateDTO(
        UserType type,
        String firstName,
        String lastName,
        String email,
        String password,
        String companyName
) {
    public User toEntity() {
        User user = new User();
        user.setType(this.type);
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setEmail(this.email);
        user.setPassword(this.password);
        user.setCompanyName(this.companyName);
        return user;
    }
}
