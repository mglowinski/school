package com.mglowinski.school.controller;

import com.mglowinski.school.dto.TeacherDto;
import com.mglowinski.school.model.Teacher;
import com.mglowinski.school.service.TeacherService;
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
public class TeacherController {

    private final TeacherService teacherService;
    private final ObjectMapper objectMapper;

    @Autowired
    public TeacherController(TeacherService teacherService,
                             ObjectMapper objectMapper) {
        this.teacherService = teacherService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/schools/{schoolId}/teachers")
    public ResponseEntity<List<TeacherDto>> getAllTeachersBySchoolId(@PathVariable Long schoolId) {
        List<Teacher> teachers = teacherService.getAllTeachersBySchoolId(schoolId);
        List<TeacherDto> teachersDto = teachers.stream()
                .map(objectMapper::mapTeacherEntityToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(teachersDto);
    }

    @PostMapping("/schools/{schoolId}/teachers")
    public ResponseEntity<TeacherDto> createTeacher(@PathVariable Long schoolId,
                                                    @RequestBody @Valid TeacherDto teacherDto) {
        Teacher teacher = objectMapper.mapTeacherDtoToEntity(teacherDto);
        Teacher createdTeacher = teacherService.createTeacher(schoolId, teacher);
        return ResponseEntity.status(HttpStatus.CREATED).body(objectMapper.mapTeacherEntityToDto(createdTeacher));
    }

}
