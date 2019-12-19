package com.mglowinski.school.service;

import com.mglowinski.school.model.Subject;

import java.util.List;

public interface SubjectService {

    List<Subject> getAllSubjects();

    Subject createSubject(Long schoolId, Subject subject);
}
