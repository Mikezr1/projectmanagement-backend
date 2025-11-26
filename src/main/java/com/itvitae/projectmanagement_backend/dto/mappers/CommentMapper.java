package com.itvitae.projectmanagement_backend.dto.mappers;

import com.itvitae.projectmanagement_backend.dto.comment.CommentCreateDTO;
import com.itvitae.projectmanagement_backend.dto.comment.CommentSummaryDTO;
import com.itvitae.projectmanagement_backend.dto.comment.CommentUpdateDTO;
import com.itvitae.projectmanagement_backend.dto.user.UserSummaryDTO;
import com.itvitae.projectmanagement_backend.models.Comment;
import com.itvitae.projectmanagement_backend.models.Task;
import com.itvitae.projectmanagement_backend.models.User;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public Comment toEntity(CommentCreateDTO dto, User user, Task task) {
        Comment comment = new Comment();
        comment.setDescription(dto.description());
        comment.setUser(user);
        comment.setTask(task);
        return comment;
    }

    public void CommentUpdateDescription(Comment comment, CommentUpdateDTO dto) {
        comment.setDescription(dto.description());
    }

    public CommentSummaryDTO toDTO(Comment comment) {
        User user = comment.getUser();
        UserSummaryDTO userDTO = user == null
                ? null : new UserSummaryDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getRole());

        return new CommentSummaryDTO(
                comment.getId(),
                comment.getDescription(),
                userDTO,
                comment.getTask() != null ? comment.getTask().getCreatedAt() : null,
                comment.getTask() != null ? comment.getTask().getUpdatedAt() : null
        );
    }
}
