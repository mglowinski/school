package com.mglowinski.school.service;

import com.mglowinski.school.model.School;
import com.mglowinski.school.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;

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

    @Override
    public School getSchoolById(Long id) {
        return schoolRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("School not found with id: " + id));
    }

    @Override
    public void updateAddress(Long id, Map<String, Object> updateFields) {
        School school = getSchoolById(id);

        if (updateFields.containsKey("street")) {
            school.getAddress().setStreet((String) updateFields.get("street"));
        }

        if (updateFields.containsKey("city")) {
            school.getAddress().setCity((String) updateFields.get("city"));
        }

        if (updateFields.containsKey("zipCode")) {
            school.getAddress().setZipCode((String) updateFields.get("zipCode"));
        }

        schoolRepository.save(school);
    }

}
