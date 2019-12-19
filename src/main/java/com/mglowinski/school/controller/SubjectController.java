package com.mglowinski.school.controller;

import com.mglowinski.school.dto.CreateSubjectDto;
import com.mglowinski.school.dto.SubjectDto;
import com.mglowinski.school.model.Subject;
import com.mglowinski.school.service.SubjectService;
import com.mglowinski.school.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/schools/{schoolId}/subjects")
    public ResponseEntity<List<SubjectDto>> getAllSubjectsBySchoolId(@PathVariable Long schoolId) {
        List<Subject> subjects = subjectService.getAllSubjectsBySchoolId(schoolId);
        List<SubjectDto> subjectsDto = subjects.stream()
                .map(objectMapper::mapSubjectEntityToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(subjectsDto);
    }

    @PostMapping("/schools/{schoolId}/subjects")
    public ResponseEntity<SubjectDto> createSubject(@PathVariable Long schoolId,
                                                    @RequestBody @Valid CreateSubjectDto createSubjectDto) {
        Subject subject = objectMapper.mapCreateSubjectDtoToEntity(createSubjectDto);
        Subject createdSubject = subjectService.createSubject(schoolId, subject);
        return ResponseEntity.status(HttpStatus.CREATED).body(objectMapper.mapSubjectEntityToDto(createdSubject));
    }

}
