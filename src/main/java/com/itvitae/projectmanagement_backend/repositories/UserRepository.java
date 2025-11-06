package com.itvitae.projectmanagement_backend.repositories;

import com.itvitae.projectmanagement_backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
