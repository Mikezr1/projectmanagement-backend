package com.itvitae.projectmanagement_backend.dto.comment;

public record CommentResponseDTO(
    long id,
    String poster,
    String description
) {}
