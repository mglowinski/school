package com.mglowinski.school.service;

import com.mglowinski.school.dto.CreateIssuedGradeDto;
import com.mglowinski.school.model.IssuedGrade;

import java.util.List;

public interface IssuedGradeService {

    List<IssuedGrade> getIssuedGradesByClassId(Long classId);

    void createIssuedGrade(Long schoolId, Long classId, CreateIssuedGradeDto createIssuedGradeDto);
}
