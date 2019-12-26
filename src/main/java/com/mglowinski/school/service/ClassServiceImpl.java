package com.mglowinski.school.service;

import com.mglowinski.school.model.Class;
import com.mglowinski.school.repository.ClassRepository;
import com.mglowinski.school.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    private final ClassRepository classRepository;
    private final SchoolRepository schoolRepository;

    @Autowired
    public ClassServiceImpl(ClassRepository classRepository,
                            SchoolRepository schoolRepository) {
        this.classRepository = classRepository;
        this.schoolRepository = schoolRepository;
    }

    @Override
    public List<Class> getAllClassesBySchoolId(Long schoolId) {
        return classRepository.findAllBySchoolId(schoolId);
    }

    @Override
    public Class createClass(Long schoolId, Class schoolClass) {
        return schoolRepository.findById(schoolId)
                .map(school -> {
                    schoolClass.setSchool(school);
                    return classRepository.save(schoolClass);
                })
                .orElseThrow(() -> new EntityNotFoundException("School not found with id: " + schoolId));
    }

}
