package com.mglowinski.school.controller;

import com.mglowinski.school.dto.CreateStudentDto;
import com.mglowinski.school.dto.StudentDto;
import com.mglowinski.school.model.Student;
import com.mglowinski.school.service.StudentService;
import com.mglowinski.school.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class StudentController {

    private final StudentService studentService;
    private final ObjectMapper objectMapper;

    @Autowired
    public StudentController(StudentService studentService,
                             ObjectMapper objectMapper) {
        this.studentService = studentService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/schools/{schoolId}/students")
    public ResponseEntity<List<StudentDto>> getAllStudentsBySchoolId(@PathVariable Long schoolId) {
        List<Student> students = studentService.getAllStudentsBySchoolId(schoolId);
        List<StudentDto> studentsDto = students.stream()
                .map(objectMapper::mapStudentEntityToStudentDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(studentsDto);
    }

    @PostMapping("/schools/{schoolId}/students")
    public ResponseEntity<StudentDto> createStudent(@PathVariable Long schoolId,
                                                    @RequestBody CreateStudentDto createStudentDto) {
        Student student = objectMapper.mapCreateStudentDtoToEntity(createStudentDto);
        Student createdStudent = studentService.createStudent(schoolId, student);
        return ResponseEntity.status(HttpStatus.CREATED).body(objectMapper.mapStudentEntityToStudentDto(createdStudent));
    }

}
