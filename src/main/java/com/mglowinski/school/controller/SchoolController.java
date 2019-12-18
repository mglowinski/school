package com.mglowinski.school.controller;

import com.mglowinski.school.dto.CreateSubjectDto;
import com.mglowinski.school.dto.SchoolDto;
import com.mglowinski.school.model.School;
import com.mglowinski.school.model.Subject;
import com.mglowinski.school.service.SchoolService;
import com.mglowinski.school.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SchoolController {

    private final SchoolService schoolService;
    private final ObjectMapper objectMapper;

    @Autowired
    public SchoolController(SchoolService schoolService, ObjectMapper modelMapper) {
        this.schoolService = schoolService;
        this.objectMapper = modelMapper;
    }

    @GetMapping("/schools")
    public ResponseEntity<List<SchoolDto>> getAllSchools() {
        List<School> schools = schoolService.getAllSchools();
        List<SchoolDto> schoolsDto = schools.stream()
                .map(objectMapper::mapSchoolEntityToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(schoolsDto);
    }

    @PostMapping("/schools")
    public ResponseEntity<SchoolDto> createSchool(@Valid @RequestBody SchoolDto schoolDto) {
        School school = objectMapper.mapSchoolDtoToEntity(schoolDto);
        School schoolCreated = schoolService.createSchool(school);
        return ResponseEntity.status(HttpStatus.CREATED).body(objectMapper.mapSchoolEntityToDto(schoolCreated));
    }

    @GetMapping("/schools/{id}")
    public ResponseEntity<SchoolDto> getSchoolById(@PathVariable Long id) {
        SchoolDto schoolDto = objectMapper.mapSchoolEntityToDto(schoolService.getSchoolById(id));
        return ResponseEntity.status(HttpStatus.OK).body(schoolDto);
    }

    @DeleteMapping("/schools/{id}")
    public ResponseEntity<Void> deleteSchoolById(@PathVariable Long id) {
        schoolService.deleteSchoolById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/schools/{id}/address")
    public ResponseEntity<Void> updateSchoolAddress(@PathVariable Long id,
                                                    @RequestBody Map<String, Object> updateFields) {
        schoolService.updateAddress(id, updateFields);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/schools/{id}/subjects")
    public ResponseEntity<CreateSubjectDto> addSubjectToSchool(@PathVariable Long id,
                                                               @RequestBody CreateSubjectDto createSubjectDto) {
        Subject subject = objectMapper.mapCreateSubjectDtoToEntity(createSubjectDto);
        schoolService.addSubjectToSchool(id, subject);
        return ResponseEntity.status(HttpStatus.CREATED).body(createSubjectDto);
    }

}