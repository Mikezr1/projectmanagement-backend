package com.itvitae.projectmanagement_backend.dto.task;

import com.itvitae.projectmanagement_backend.dto.comment.CommentSummaryDTO;
import com.itvitae.projectmanagement_backend.enums.Status;

import java.util.List;

public record TaskSummaryDTO(
    long id,
    String title,
    String description,
    List<CommentSummaryDTO> comments,
    Status status
) {}
