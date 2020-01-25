package com.mglowinski.school.controller;

import com.mglowinski.school.dto.AssignedStudentDto;
import com.mglowinski.school.dto.CreateStudentDto;
import com.mglowinski.school.dto.StudentDto;
import com.mglowinski.school.dto.StudentWithoutClassDto;
import com.mglowinski.school.model.Student;
import com.mglowinski.school.service.ClassService;
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
    private final ClassService classService;
    private final ObjectMapper objectMapper;

    @Autowired
    public StudentController(StudentService studentService,
                             ClassService classService,
                             ObjectMapper objectMapper) {
        this.studentService = studentService;
        this.classService = classService;
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

    @GetMapping("/schools/{schoolId}/classes/{classId}/students")
    public ResponseEntity<List<StudentWithoutClassDto>> getAllStudentsFromClass(
            @PathVariable Long schoolId,
            @PathVariable Long classId) {
        List<Student> students = classService.getAllStudentsFromClass(schoolId, classId);
        List<StudentWithoutClassDto> studentWithoutClassesDto = students.stream()
                .map(objectMapper::mapStudentEntityToStudentWithoutClassDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(studentWithoutClassesDto);
    }

    @PostMapping("/schools/{schoolId}/classes/{classId}/students")
    public ResponseEntity<Void> addStudentToClass(
            @PathVariable Long schoolId,
            @PathVariable Long classId,
            @RequestBody AssignedStudentDto assignedStudentDto) {
        classService.addStudentToClass(schoolId, classId, assignedStudentDto);
        return ResponseEntity.noContent().build();
    }

}
