package com.itvitae.projectmanagement_backend.dto.project;

import com.itvitae.projectmanagement_backend.models.Task;
import com.itvitae.projectmanagement_backend.models.User;

import java.util.List;

public record ProjectSummaryDTO(
        long id,
        String title,
        List<User> users,
        List<Task> tasks
) {
}
