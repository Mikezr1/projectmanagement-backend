package com.itvitae.projectmanagement_backend.dto.project;

import com.itvitae.projectmanagement_backend.models.User;

import java.util.List;

public record ProjectCreateDTO(
        String title,
        List<Long> userIds
) {}
