package com.mglowinski.school.controller;

import com.mglowinski.school.model.School;
import com.mglowinski.school.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/schools")
public class SchoolController {

    private final SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping
    public ResponseEntity<List<School>> getAllSchools() {
        return ResponseEntity.ok(schoolService.getAllSchools());
    }

    @PostMapping
    public ResponseEntity<School> createSchool(@Valid @RequestBody School school) {
        return ResponseEntity.status(201).body(schoolService.createSchool(school));
    }
}