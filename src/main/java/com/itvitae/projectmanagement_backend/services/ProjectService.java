package com.itvitae.projectmanagement_backend.services;

import com.itvitae.projectmanagement_backend.dto.mappers.ProjectMapper;
import com.itvitae.projectmanagement_backend.dto.project.ProjectSummaryDTO;
import com.itvitae.projectmanagement_backend.models.Project;
import com.itvitae.projectmanagement_backend.dto.project.ProjectCreateDTO;
import com.itvitae.projectmanagement_backend.dto.project.ProjectDTO;
import com.itvitae.projectmanagement_backend.dto.project.ProjectUpdateDTO;
import com.itvitae.projectmanagement_backend.repositories.ProjectRepository;
import com.itvitae.projectmanagement_backend.repositories.TaskRepository;
import com.itvitae.projectmanagement_backend.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itvitae.projectmanagement_backend.models.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    final private ProjectRepository projectRepository;
    final private UserRepository userRepository;
    final private TaskRepository taskRepository;
    final private ProjectMapper projectMapper;

    @Autowired
    public ProjectService(ProjectRepository projectRepository,
                          UserRepository userRepository,
                          TaskRepository taskRepository,
                          ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.projectMapper = projectMapper;
    }
    public List<ProjectSummaryDTO> getAllProjects() {
        List<Project> projects = projectRepository.findAll();

        return projects.stream()
                .map(projectMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProjectDTO findById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Topic not found with ID: " + id));
        return ProjectDTO.fromEntity(project);
    }
    public ProjectDTO createProject(ProjectCreateDTO dto) {
        Project project = projectMapper.toEntity();

        User user = userRepository.findById(createDTO.userId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + createDTO.userId()));

        project.setUser(user);

        Project savedProject = projectRepository.save(project);
        return ProjectDTO.fromEntity(savedProject);
    }
    public ProjectDTO updateProject (Long id, ProjectUpdateDTO updateDTO) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Topic not found with ID: " + id));

        updateDTO.updateProject(project);
        Project savedProjects = projectRepository.save(project);
        return ProjectDTO.fromEntity(savedProjects);
    }

}
