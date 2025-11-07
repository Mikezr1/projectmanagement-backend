package com.itvitae.projectmanagement_backend.dto.mappers;

import com.itvitae.projectmanagement_backend.dto.project.ProjectCreateDTO;
import com.itvitae.projectmanagement_backend.dto.project.ProjectSummaryDTO;
import com.itvitae.projectmanagement_backend.models.Project;

public class ProjectMapper {
    public Project toEntity(ProjectCreateDTO dto) {
        Project project = new Project();
        project.setTitle(dto.title());

        return project;
    }



    public Project toDTO(Project project) {
        return new ProjectSummaryDTO(
                project.getId(),
                project.getTitle(),
                project.getUsers(),
                project.getTasks()
        )
    }
}
