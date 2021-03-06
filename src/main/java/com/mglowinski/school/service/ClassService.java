package com.mglowinski.school.service;

import com.mglowinski.school.dto.AssignedStudentDto;
import com.mglowinski.school.dto.AssignedSubjectWithTeacherDto;
import com.mglowinski.school.dto.AssignedTutorDto;
import com.mglowinski.school.model.Class;
import com.mglowinski.school.model.Student;

import java.util.List;

public interface ClassService {

    List<Class> getAllClassesBySchoolId(Long schoolId);

    Class createClass(Long schoolId, Class schoolClass);

    void assignTutor(Long schoolId, Long classId, AssignedTutorDto assignedTutorDto);

    void assignSubjectWithTeacher(Long schoolId, Long classId,
                                  AssignedSubjectWithTeacherDto assignedSubjectWithTeacherDto);

    Class getClassBySchoolIdAndClassId(Long schoolId, Long classId);

    List<Student> getAllStudentsFromClass(Long schoolId, Long classId);

    void addStudentToClass(Long schoolId, Long classId, AssignedStudentDto assignedStudentDto);
}
