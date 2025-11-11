package com.itvitae.projectmanagement_backend.dto.project;

import java.util.List;

public record ProjectCreateDTO(
        String title,
        List<Long> userIds
) {}
