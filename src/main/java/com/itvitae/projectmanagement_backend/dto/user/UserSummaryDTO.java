package com.itvitae.projectmanagement_backend.dto.user;

import com.itvitae.projectmanagement_backend.enums.UserType;
import com.itvitae.projectmanagement_backend.models.User;

public record UserSummaryDTO(
        Long id,
        UserType type,
        String firstName,
        String lastName,
        String email,
        String companyName
) {
    public static UserSummaryDTO fromEntity(User user) {
        return new UserSummaryDTO(
                user.getId(),
                user.getType(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getCompanyName()
        );
    }
}
