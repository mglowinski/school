package com.mglowinski.school.service;

import com.mglowinski.school.model.Comment;
import com.mglowinski.school.repository.CommentRepository;
import com.mglowinski.school.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository,
                              StudentRepository studentRepository) {
        this.commentRepository = commentRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Comment createComment(Long schoolId, Long studentId, Comment comment) {
        return studentRepository.findByIdAndSchoolId(studentId, schoolId)
                .map(student -> {
                    comment.setStudent(student);
                    return commentRepository.save(comment);
                })
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));
    }

    @Override
    public List<Comment> getCommentsByStudentId(Long studentId) {
        return commentRepository.findAllByStudentId(studentId);
    }

}
