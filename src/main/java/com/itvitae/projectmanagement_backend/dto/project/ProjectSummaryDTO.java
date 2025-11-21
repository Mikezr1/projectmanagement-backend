package com.itvitae.projectmanagement_backend.dto.project;

import com.itvitae.projectmanagement_backend.dto.task.TaskSummaryDTO;
import com.itvitae.projectmanagement_backend.dto.user.UserSummaryDTO;

import java.util.List;

public record ProjectSummaryDTO(
        Long id,
        String title,
        List<UserSummaryDTO> users,
        List<TaskSummaryDTO> tasks
) {
}
