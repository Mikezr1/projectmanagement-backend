package com.itvitae.projectmanagement_backend.repositories;

import com.itvitae.projectmanagement_backend.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
