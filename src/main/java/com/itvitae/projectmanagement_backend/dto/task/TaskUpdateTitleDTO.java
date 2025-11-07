package com.itvitae.projectmanagement_backend.dto.task;

import com.itvitae.projectmanagement_backend.models.User;

public record TaskUpdateTitleDTO(
   Long id,
   String title,
   User user
) {}
