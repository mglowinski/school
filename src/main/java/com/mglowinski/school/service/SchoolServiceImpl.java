package com.mglowinski.school.service;

import com.mglowinski.school.model.School;
import com.mglowinski.school.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;

    @Autowired
    public SchoolServiceImpl(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Override
    public List<School> getAllSchools() {
        return schoolRepository.findAll();
    }

    @Override
    public School createSchool(School school) {
        return schoolRepository.save(school);
    }
}
