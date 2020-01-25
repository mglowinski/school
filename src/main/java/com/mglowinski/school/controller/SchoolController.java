package com.mglowinski.school.controller;

import com.mglowinski.school.dto.CreateSchoolDto;
import com.mglowinski.school.dto.SchoolDto;
import com.mglowinski.school.model.School;
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
    public ResponseEntity<SchoolDto> createSchool(@Valid @RequestBody CreateSchoolDto createSchoolDto) {
        School school = objectMapper.mapCreateSchoolDtoToEntity(createSchoolDto);
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
                                                    @RequestBody Map<String, Object> updatedFields) {
        schoolService.updateAddress(id, updatedFields);
        return ResponseEntity.noContent().build();
    }

}