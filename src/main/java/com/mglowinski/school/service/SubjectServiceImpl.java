package com.mglowinski.school.service;

import com.mglowinski.school.model.Subject;
import com.mglowinski.school.repository.SchoolRepository;
import com.mglowinski.school.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final SchoolRepository schoolRepository;

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository,
                              SchoolRepository schoolRepository) {
        this.subjectRepository = subjectRepository;
        this.schoolRepository = schoolRepository;
    }

    @Override
    public List<Subject> getAllSubjectsBySchoolId(Long schoolId) {
        return subjectRepository.findAllBySchoolId(schoolId);
    }

    @Override
    public Subject createSubject(Long schoolId, Subject subject) {
        return schoolRepository.findById(schoolId)
                .map(school -> {
                    subject.setSchool(school);
                    return subjectRepository.save(subject);
                })
                .orElseThrow(() -> new EntityNotFoundException("School not found with id: " + schoolId));
    }

}
