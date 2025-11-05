package com.itvitae.projectmanagement_backend.dto.task;

import com.itvitae.projectmanagement_backend.dto.comment.CommentResponseDTO;

import java.util.List;

public record TaskResponseDTO(
    long id,
    String title,
    String description,
    List<CommentResponseDTO> comments,
    String status
) {}
