package com.itvitae.projectmanagement_backend.dto.comment;

import com.itvitae.projectmanagement_backend.models.User;

public record CommentResponseDTO(
    long id,
    User poster,
    String description
) {}
