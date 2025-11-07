package com.itvitae.projectmanagement_backend.repositories;

import com.itvitae.projectmanagement_backend.models.Notification;
import com.itvitae.projectmanagement_backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    User findByRecipientOrderByCreatedAtDesc(User currentUser);
}
