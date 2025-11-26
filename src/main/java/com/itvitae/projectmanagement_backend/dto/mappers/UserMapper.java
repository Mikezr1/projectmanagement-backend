package com.itvitae.projectmanagement_backend.dto.mappers;

import com.itvitae.projectmanagement_backend.dto.user.UserChangePasswordDTO;
import com.itvitae.projectmanagement_backend.dto.user.UserCreateDTO;
import com.itvitae.projectmanagement_backend.dto.user.UserSummaryDTO;
import com.itvitae.projectmanagement_backend.dto.user.UserUpdateDTO;
import com.itvitae.projectmanagement_backend.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(UserCreateDTO dto) {
        User user = new User();
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        user.setRole(dto.role());
        user.setCompanyName(dto.companyName());
        return user;
    }

    public void updateFromDTO(User user, UserUpdateDTO dto) {
        if (dto.firstName() != null) {
            user.setFirstName(dto.firstName());
        }
        if (dto.lastName() != null) {
            user.setLastName(dto.lastName());
        }
        if (dto.role() != null) {
            user.setRole(dto.role());
        }
        //TODO: More updates here
    }

    public UserSummaryDTO toDTO(User user) {
        return new UserSummaryDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole()
        );
    }
}
