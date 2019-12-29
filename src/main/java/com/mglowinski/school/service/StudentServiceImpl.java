package com.mglowinski.school.service;

import com.mglowinski.school.model.Student;
import com.mglowinski.school.repository.SchoolRepository;
import com.mglowinski.school.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final SchoolRepository schoolRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository,
                              SchoolRepository schoolRepository) {
        this.studentRepository = studentRepository;
        this.schoolRepository = schoolRepository;
    }

    @Override
    public List<Student> getAllStudentsBySchoolId(Long schoolId) {
        return studentRepository.findAllBySchoolId(schoolId);
    }

    @Override
    public Student createStudent(Long schoolId, Student student) {
        return schoolRepository.findById(schoolId)
                .map(school -> {
                    student.setSchool(school);
                    return studentRepository.save(student);
                })
                .orElseThrow(() -> new EntityNotFoundException("School not found with id: " + schoolId));
    }

}
