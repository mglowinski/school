package com.mglowinski.school.service;

import com.mglowinski.school.model.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudentsBySchoolId(Long schoolId);

    Student createStudent(Long schoolId, Student student);
}
