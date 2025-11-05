package com.itvitae.projectmanagement_backend.repositories;

import com.itvitae.projectmanagement_backend.Models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long>  {
}
