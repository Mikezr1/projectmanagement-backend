package com.itvitae.projectmanagement_backend.repositories;

import com.itvitae.projectmanagement_backend.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
