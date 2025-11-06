package com.itvitae.projectmanagement_backend.services;

import com.itvitae.projectmanagement_backend.Models.Project;
import com.itvitae.projectmanagement_backend.dto.project.ProjectCreateDTO;
import com.itvitae.projectmanagement_backend.dto.project.ProjectDTO;
import com.itvitae.projectmanagement_backend.dto.project.ProjectUpdateDTO;
import com.itvitae.projectmanagement_backend.repositories.ProjectRepository;
import com.itvitae.projectmanagement_backend.repositories.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itvitae.projectmanagement_backend.Models.User;

import java.util.List;

@Service
public class ProjectService {

    final private ProjectRepository projectRepository;
    final private TaskRepository taskRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository,
                          TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }
    public List<ProjectDTO> findAll() {
        return projectRepository.findAll()
                .stream()
                .map(ProjectDTO::fromEntity)
                .toList();
    }
    public ProjectDTO findById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Topic not found with ID: " + id));
        return ProjectDTO.fromEntity(project);
    }
    public ProjectDTO createProject(ProjectCreateDTO createDTO) {
        Project project = createDTO.toEntity();

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
