package com.itvitae.projectmanagement_backend.services;

import com.itvitae.projectmanagement_backend.dto.comment.CommentCreateDTO;
import com.itvitae.projectmanagement_backend.dto.comment.CommentSummaryDTO;
import com.itvitae.projectmanagement_backend.dto.comment.CommentUpdateDescriptionDTO;
import com.itvitae.projectmanagement_backend.dto.mappers.CommentMapper;
import com.itvitae.projectmanagement_backend.exceptions.CommentNotFoundException;
import com.itvitae.projectmanagement_backend.exceptions.TaskNotFoundException;
import com.itvitae.projectmanagement_backend.exceptions.UserNotFoundException;
import com.itvitae.projectmanagement_backend.models.Comment;
import com.itvitae.projectmanagement_backend.models.Task;
import com.itvitae.projectmanagement_backend.models.User;
import com.itvitae.projectmanagement_backend.repositories.CommentRepository;
import com.itvitae.projectmanagement_backend.repositories.TaskRepository;
import com.itvitae.projectmanagement_backend.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final CommentMapper commentMapper;

    @Autowired
    public CommentService(CommentRepository commentRepository, UserRepository userRepository, TaskRepository taskRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.commentMapper = commentMapper;
    }

    @Transactional
    public CommentSummaryDTO createComment(CommentCreateDTO dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Task task = taskRepository.findById(dto.taskId())
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));

        Comment comment = CommentMapper.toEntity(dto, user, task);
        commentRepository.save(comment);
        return commentMapper.toDTO(comment);
    }

    @Transactional
    public List<CommentSummaryDTO> getAllCommentsByTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));

        return commentRepository.findAllByTask(task)
                .stream()
                .map(commentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentSummaryDTO updateDescription(Long id, CommentUpdateDescriptionDTO dto) {
        Comment comment = commentRepository.findById(id)
            .orElseThrow(() -> new CommentNotFoundException("Comment not found"));

        commentMapper.CommentUpdateDescription(comment, dto);
        commentRepository.save(comment);
        return commentMapper.toDTO(comment);
    }

    @Transactional
    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found"));

        commentRepository.delete(comment);
    }
}
