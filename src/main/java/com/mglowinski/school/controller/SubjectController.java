package com.mglowinski.school.controller;

import com.mglowinski.school.dto.SubjectDto;
import com.mglowinski.school.model.Subject;
import com.mglowinski.school.service.SubjectService;
import com.mglowinski.school.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SubjectController {

    private final SubjectService subjectService;
    private final ObjectMapper objectMapper;

    @Autowired
    public SubjectController(SubjectService subjectService,
                             ObjectMapper objectMapper) {
        this.subjectService = subjectService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/subjects")
    public ResponseEntity<List<SubjectDto>> getAllSubjects() {
        List<Subject> subjects = subjectService.getAllSubjects();
        List<SubjectDto> subjectsDto = subjects.stream()
                .map(objectMapper::mapSubjectEntityToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(subjectsDto);
    }

}
