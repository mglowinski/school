package com.mglowinski.school.service;

import com.mglowinski.school.model.Comment;

import java.util.List;

public interface CommentService {

    Comment createComment(Long schoolId, Long studentId, Comment comment);

    List<Comment> getCommentsByStudentId(Long studentId);
}
