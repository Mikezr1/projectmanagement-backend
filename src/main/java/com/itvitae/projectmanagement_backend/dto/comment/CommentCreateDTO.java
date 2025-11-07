package com.itvitae.projectmanagement_backend.dto.comment;

public record CommentCreateDTO(
    String description,
    Long userId,
    Long taskId
) {}
