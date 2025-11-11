package com.itvitae.projectmanagement_backend.dto.mappers;

import com.itvitae.projectmanagement_backend.dto.project.ProjectCreateDTO;
import com.itvitae.projectmanagement_backend.dto.project.ProjectSummaryDTO;
import com.itvitae.projectmanagement_backend.dto.project.ProjectUpdateDTO;
import com.itvitae.projectmanagement_backend.dto.task.TaskSummaryDTO;
import com.itvitae.projectmanagement_backend.dto.user.UserSummaryDTO;
import com.itvitae.projectmanagement_backend.models.Project;
import com.itvitae.projectmanagement_backend.models.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectMapper {
    private final TaskMapper taskMapper;

    public ProjectMapper(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    public Project toEntity(ProjectCreateDTO dto, List<User> users) {
        Project project = new Project();
        project.setTitle(dto.title());
        project.setUsers(users);
        return project;
    }

    public void updateFromDTO(Project project, ProjectUpdateDTO dto, List<User> users) {
        if (dto.title() != null) {
            project.setTitle(dto.title());
        }
        if (users != null) {
            project.setUsers(users);
        }
    }

    public ProjectSummaryDTO toDTO(Project project) {
        List<UserSummaryDTO> userDTOs = project.getUsers() == null
                ? List.of() : project.getUsers().stream()
                .map(user -> new UserSummaryDTO(user.getId(), user.getFirstName(), user.getEmail(), user.getRole()))
                .collect(Collectors.toList());

        List<TaskSummaryDTO> taskDTOs = project.getTasks() == null
                ? List.of() : project.getTasks().stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());

        return new ProjectSummaryDTO(
                project.getId(),
                project.getTitle(),
                userDTOs,
                taskDTOs
        );
    }
}
