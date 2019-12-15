package com.mglowinski.school.service;

import com.mglowinski.school.model.School;

import java.util.List;

public interface SchoolService {

    List<School> getAllSchools();

    School createSchool(School school);
}
