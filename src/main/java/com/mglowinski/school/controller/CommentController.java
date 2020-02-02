package com.mglowinski.school.controller;

import com.mglowinski.school.dto.CommentDto;
import com.mglowinski.school.dto.CreateCommentDto;
import com.mglowinski.school.model.Comment;
import com.mglowinski.school.service.CommentService;
import com.mglowinski.school.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;
    private final ObjectMapper objectMapper;

    @Autowired
    public CommentController(CommentService commentService,
                             ObjectMapper objectMapper) {
        this.commentService = commentService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/schools/{schoolId}/students/{studentId}/comments")
    public ResponseEntity<List<CommentDto>> getAllStudentComments(@PathVariable Long studentId) {
        List<Comment> comments = commentService.getCommentsByStudentId(studentId);
        List<CommentDto> commentsDto = comments.stream()
                .map(objectMapper::mapCommentEntityToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(commentsDto);
    }

    @PostMapping("/schools/{schoolId}/students/{studentId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable Long schoolId,
                                                    @PathVariable Long studentId,
                                                    @RequestBody CreateCommentDto createCommentDto) {
        Comment comment = objectMapper.mapCreateCommentDtoToEntity(createCommentDto);
        Comment savedComment = commentService.createComment(schoolId, studentId, comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(objectMapper.mapCommentEntityToDto(savedComment));
    }

}
