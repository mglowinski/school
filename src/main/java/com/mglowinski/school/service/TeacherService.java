package com.mglowinski.school.service;

import com.mglowinski.school.dto.AssignedSubjectDto;
import com.mglowinski.school.model.Teacher;

import java.util.List;

public interface TeacherService {

    List<Teacher> getAllTeachersBySchoolId(Long schoolId);

    Teacher createTeacher(Long schoolId, Teacher teacher);

    Teacher assignSubject(Long schoolId, Long teacherId, AssignedSubjectDto assignedSubjectDto);
}
