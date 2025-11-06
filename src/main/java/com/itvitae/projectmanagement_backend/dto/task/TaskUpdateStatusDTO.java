package com.itvitae.projectmanagement_backend.dto.task;

import com.itvitae.projectmanagement_backend.enums.Status;

public record TaskUpdateStatusDTO(
        Long id,
        Status status
) {
}
