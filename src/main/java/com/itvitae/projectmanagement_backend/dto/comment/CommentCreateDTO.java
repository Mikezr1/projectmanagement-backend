package com.itvitae.projectmanagement_backend.dto.comment;

public record CommentCreateDTO(
    String text,
    Long userId,
    Long taskId
) {}
