package com.itvitae.projectmanagement_backend.controllers;

import com.itvitae.projectmanagement_backend.dto.comment.CommentCreateDTO;
import com.itvitae.projectmanagement_backend.dto.comment.CommentSummaryDTO;
import com.itvitae.projectmanagement_backend.dto.comment.CommentUpdateDTO;
import com.itvitae.projectmanagement_backend.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@CrossOrigin(origins = "*")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<CommentSummaryDTO> createComment(@RequestBody CommentCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentSummaryDTO> getCommentById(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.getCommentById(id));
    }

    @GetMapping
    public ResponseEntity<List<CommentSummaryDTO>> getAllComments() {
        return ResponseEntity.ok(commentService.getAllComments());
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<List<CommentSummaryDTO>> getCommentsByTask(@PathVariable Long id) {
        List<CommentSummaryDTO> response = commentService.getAllCommentsByTask(id);
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getAllCommentsByTask(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentSummaryDTO> updateComment(@PathVariable Long id, CommentUpdateDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.updateComment(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
