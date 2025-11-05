package com.itvitae.projectmanagement_backend.dto.mappers;

import com.itvitae.projectmanagement_backend.dto.comment.CommentResponseDTO;
import com.itvitae.projectmanagement_backend.models.Comment;
import com.itvitae.projectmanagement_backend.models.Task;
import com.itvitae.projectmanagement_backend.models.User;

public class CommentMapper {
    public Comment toEntity(CommentResponseDTO dto, User user, Task task) {
        Comment comment = new Comment();
        comment.setPoster(user);
        comment.setDescription(dto.description());
        comment.setTask(task);
        return comment;
    }

    public void

    public CommentResponseDTO toDTO(Comment comment) {
        return new CommentResponseDTO(
                comment.getId(),
                comment.getPoster(),
                comment.getDescription()
        );
    }
}
