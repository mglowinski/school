package com.mglowinski.school.controller;

import com.mglowinski.school.dto.CreateIssuedGradeDto;
import com.mglowinski.school.dto.IssuedGradeDto;
import com.mglowinski.school.model.IssuedGrade;
import com.mglowinski.school.service.IssuedGradeService;
import com.mglowinski.school.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class IssuedGradeController {

    private final IssuedGradeService issuedGradeService;
    private final ObjectMapper objectMapper;

    @Autowired
    public IssuedGradeController(IssuedGradeService issuedGradeService,
                                 ObjectMapper objectMapper) {
        this.issuedGradeService = issuedGradeService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/schools/{schoolId}/classes/{classId}/issued-grades")
    public ResponseEntity<List<IssuedGradeDto>> getIssuedGradesByClassId(@PathVariable Long classId) {
        List<IssuedGrade> issuedGrades = issuedGradeService.getIssuedGradesByClassId(classId);
        List<IssuedGradeDto> issuedGradesDto = issuedGrades.stream()
                .map(objectMapper::mapIssuedGradeEntityToIssuedGradeDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(issuedGradesDto);
    }

    @PostMapping("/schools/{schoolId}/classes/{classId}/issued-grades")
    public ResponseEntity<Void> createIssuedGrade(@PathVariable Long schoolId,
                                                  @PathVariable Long classId,
                                                  @Valid @RequestBody CreateIssuedGradeDto createIssuedGradeDto) {
        issuedGradeService.createIssuedGrade(schoolId, classId, createIssuedGradeDto);
        return ResponseEntity.noContent().build();
    }

}
