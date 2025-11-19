package com.itvitae.projectmanagement_backend.repositories;


import com.itvitae.projectmanagement_backend.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByUsers_Id(Long userId);
}
