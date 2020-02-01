package com.mglowinski.school.controller;

import com.mglowinski.school.dto.AssignedSubjectWithTeacherDto;
import com.mglowinski.school.dto.AssignedTutorDto;
import com.mglowinski.school.dto.ClassDto;
import com.mglowinski.school.dto.ClassSubjectTeacherDto;
import com.mglowinski.school.model.Class;
import com.mglowinski.school.service.ClassService;
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
public class ClassController {

    private final ClassService classService;
    private final ObjectMapper objectMapper;

    @Autowired
    public ClassController(ClassService classService,
                           ObjectMapper objectMapper) {
        this.classService = classService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/schools/{schoolId}/classes")
    public ResponseEntity<List<ClassDto>> getAllClassesBySchoolId(@PathVariable Long schoolId) {
        List<Class> classes = classService.getAllClassesBySchoolId(schoolId);
        List<ClassDto> classesDto = classes.stream()
                .map(objectMapper::mapClassEntityToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(classesDto);
    }

    @PostMapping("/schools/{schoolId}/classes")
    public ResponseEntity<ClassDto> createClass(@PathVariable Long schoolId,
                                                @RequestBody @Valid ClassDto classDto) {
        Class schoolClass = objectMapper.mapClassDtoToEntity(classDto);
        Class createdClass = classService.createClass(schoolId, schoolClass);
        return ResponseEntity.status(HttpStatus.CREATED).body(objectMapper.mapClassEntityToDto(createdClass));
    }

    @PutMapping("/schools/{schoolId}/classes/{classId}/tutor/assign")
    public ResponseEntity<Void> assignTutorToClass(@PathVariable Long schoolId,
                                                   @PathVariable Long classId,
                                                   @RequestBody AssignedTutorDto assignedTutor) {
        classService.assignTutor(schoolId, classId, assignedTutor);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/schools/{schoolId}/classes/{classId}")
    public ResponseEntity<ClassSubjectTeacherDto> getClassBySchoolIdAndClassId(@PathVariable Long schoolId,
                                                                               @PathVariable Long classId) {
        Class schoolClass = classService.getClassBySchoolIdAndClassId(schoolId, classId);
        return ResponseEntity.ok(objectMapper.mapClassEntityToClassSubjectTeacherDto(schoolClass));
    }

    @PutMapping("/schools/{schoolId}/classes/{classId}/subjects-teachers")
    public ResponseEntity<Void> assignSubjectWithTeacherToClass(@PathVariable Long schoolId,
                                                                @PathVariable Long classId,
                                                                @RequestBody AssignedSubjectWithTeacherDto
                                                                        assignedSubjectWithTeacherDto) {
        classService.assignSubjectWithTeacher(schoolId, classId, assignedSubjectWithTeacherDto);
        return ResponseEntity.noContent().build();
    }

}
