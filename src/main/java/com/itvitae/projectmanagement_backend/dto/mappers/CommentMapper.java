package com.itvitae.projectmanagement_backend.dto.mappers;

import com.itvitae.projectmanagement_backend.dto.comment.CommentSummaryDTO;
import com.itvitae.projectmanagement_backend.dto.comment.CommentUpdateDescriptionDTO;
import com.itvitae.projectmanagement_backend.models.Comment;
import com.itvitae.projectmanagement_backend.models.Task;
import com.itvitae.projectmanagement_backend.models.User;

public class CommentMapper {
    public Comment toEntity(CommentSummaryDTO dto, User user, Task task) {
        Comment comment = new Comment();
        comment.setPoster(user);
        comment.setDescription(dto.description());
        comment.setTask(task);
        return comment;
    }

    public void CommentUpdateDescription(Comment comment, CommentUpdateDescriptionDTO dto) {
        comment.setDescription(dto.description());
    }

    public CommentSummaryDTO toDTO(Comment comment) {
        return new CommentSummaryDTO(
                comment.getId(),
                comment.getPoster(),
                comment.getDescription()
        );
    }
}
