package com.mglowinski.school.service;

import com.mglowinski.school.model.School;

import java.util.List;
import java.util.Map;

public interface SchoolService {

    List<School> getAllSchools();

    School createSchool(School school);

    School getSchoolById(Long id);

    void updateAddress(Long id, Map<String, Object> updateFields);

    void deleteSchoolById(Long id);
}
