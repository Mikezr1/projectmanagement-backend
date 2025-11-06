package com.itvitae.projectmanagement_backend.dto.user;

import com.itvitae.projectmanagement_backend.dto.project.ProjectSummaryDTO;
import com.itvitae.projectmanagement_backend.enums.UserType;
import com.itvitae.projectmanagement_backend.models.Comment;
import com.itvitae.projectmanagement_backend.models.Project;
import com.itvitae.projectmanagement_backend.models.Task;
import com.itvitae.projectmanagement_backend.models.User;

import java.util.List;

public record UserDTO(
        Long id,
        UserType type,
        String firstName,
        String lastName,
        String email,
        String companyName,
        List<CommentSummaryDTO> commentDTOs,
        List<ProjectSummaryDTO> projectDTOs,
        List<TaskSummaryDTO> taskDTOs

) {
    public static UserDTO fromEntity(User user) {
        List<CommentSummaryDTO> commentDTOs = user.getComments()
                .stream()
                .map(CommentSummaryDTO::fromEntity)
                .toList();
        List<ProjectSummaryDTO> projectDTOs = user.getProjects()
                .stream()
                .map(ProjectSummaryDTO::fromEntity)
                .toList();
        List<TaskSummaryDTO> taskDTOs = user.getTasks()
                .stream()
                .map(TaskSummaryDTO::fromEntity)
                .toList();

        return new UserDTO(
                user.getId(),
                user.getType(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getCompanyName(),
                commentDTOs,
                projectDTOs,
                taskDTOs
        );
    }
}
