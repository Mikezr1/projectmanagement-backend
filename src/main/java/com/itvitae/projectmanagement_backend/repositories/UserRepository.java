package com.itvitae.projectmanagement_backend.repositories;

import com.itvitae.projectmanagement_backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String firstName, String lastName, String email);
//    List<User> findByLastnameContainingIgnoreCase(String lastName);
}
