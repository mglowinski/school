package com.mglowinski.school.service;

import com.mglowinski.school.model.Teacher;
import com.mglowinski.school.repository.SchoolRepository;
import com.mglowinski.school.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final SchoolRepository schoolRepository;
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(SchoolRepository schoolRepository,
                              TeacherRepository teacherRepository) {
        this.schoolRepository = schoolRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Teacher> getAllTeachersBySchoolId(Long schoolId) {
        return teacherRepository.findAllBySchoolId(schoolId);
    }

    @Override
    public Teacher createTeacher(Long schoolId, Teacher teacher) {
        return schoolRepository.findById(schoolId)
                .map(school -> {
                    teacher.setSchool(school);
                    return teacherRepository.save(teacher);
                })
                .orElseThrow(() -> new EntityNotFoundException("School not found with id: " + schoolId));
    }

}
