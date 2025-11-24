package com.itvitae.projectmanagement_backend.services;

import com.itvitae.projectmanagement_backend.dto.mappers.ProjectMapper;
import com.itvitae.projectmanagement_backend.dto.project.ProjectSummaryDTO;
import com.itvitae.projectmanagement_backend.exceptions.ProjectNotFoundException;
import com.itvitae.projectmanagement_backend.exceptions.UserNotFoundException;
import com.itvitae.projectmanagement_backend.models.Project;
import com.itvitae.projectmanagement_backend.dto.project.ProjectCreateDTO;
import com.itvitae.projectmanagement_backend.dto.project.ProjectUpdateDTO;
import com.itvitae.projectmanagement_backend.repositories.ProjectRepository;
import com.itvitae.projectmanagement_backend.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.itvitae.projectmanagement_backend.models.User;

import java.awt.color.ProfileDataException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProjectService {

    final private ProjectRepository projectRepository;
    final private UserRepository userRepository;
    final private ProjectMapper projectMapper;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.projectMapper = projectMapper;
    }

    public ProjectSummaryDTO createProject(ProjectCreateDTO dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new UserNotFoundException("User not found: " + dto.userId()));

        Project project = projectMapper.toEntity(dto);
        project.setUsers(List.of(user));
        projectRepository.save(project);
        return projectMapper.toDTO(project);
    }

    public ProjectSummaryDTO addUsersToProject(Long projectId, List<Long> userIds) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found: " + projectId));

        List<User> users = userIds.stream()
                .map(userId -> userRepository.findById(userId)
                        .orElseThrow(() -> new UserNotFoundException("User not found: " + userId)))
                .collect(Collectors.toList());

        project.getUsers().addAll(users);
        projectRepository.save(project);
        return projectMapper.toDTO(project);
    }

    @Transactional
    public ProjectSummaryDTO deleteUsersFromProject(Long projectId, List<Long> userIds) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found: " + projectId));
        project.getUsers().removeIf(user -> userIds.contains(user.getId()));
        projectRepository.save(project);
        return findById(projectId);
    }

    public List<ProjectSummaryDTO> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(projectMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ProjectSummaryDTO> getProjectsByUser(Long userId) {
        return projectRepository.findByUsers_Id(userId)
                .stream()
                .map(projectMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProjectSummaryDTO findById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Topic not found with ID: " + id));
        return projectMapper.toDTO(project);
    }

    public ProjectSummaryDTO updateProject (Long id, ProjectUpdateDTO dto) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Topic not found with ID: " + id));

        List<User> users = new ArrayList<>();
        if (dto.userIds() != null) {
            users = dto.userIds().stream()
                    .map(userId -> userRepository.findById(id)
                            .orElseThrow(() -> new UserNotFoundException("User not found: " + userId)))
                    .collect(Collectors.toList());
        }
        projectMapper.updateFromDTO(project, dto, users);

        Project updatedProject = projectRepository.save(project);
        return projectMapper.toDTO(updatedProject);
    }

    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ProfileDataException("project not found"));
        projectRepository.delete(project);
    }
}
