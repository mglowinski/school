package com.mglowinski.school.service;

import com.mglowinski.school.dto.AssignedSubjectDto;
import com.mglowinski.school.model.Subject;
import com.mglowinski.school.model.SubjectTeacher;
import com.mglowinski.school.model.Teacher;
import com.mglowinski.school.repository.SchoolRepository;
import com.mglowinski.school.repository.SubjectRepository;
import com.mglowinski.school.repository.SubjectTeacherRepository;
import com.mglowinski.school.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final SchoolRepository schoolRepository;
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    private final SubjectTeacherRepository subjectTeacherRepository;

    @Autowired
    public TeacherServiceImpl(SchoolRepository schoolRepository,
                              TeacherRepository teacherRepository,
                              SubjectRepository subjectRepository,
                              SubjectTeacherRepository subjectTeacherRepository) {
        this.schoolRepository = schoolRepository;
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
        this.subjectTeacherRepository = subjectTeacherRepository;
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

    @Override
    public Teacher assignSubject(Long schoolId,
                                 Long teacherId,
                                 AssignedSubjectDto assignedSubjectDto) {
        Subject subject = subjectRepository
                .findByIdAndSchoolId(assignedSubjectDto.getId(), schoolId)
                .orElseThrow(() -> new EntityNotFoundException("Subject not found with id: " + assignedSubjectDto.getId()));

        Teacher teacher = teacherRepository
                .findByIdAndSchoolId(teacherId, schoolId)
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found with id: " + teacherId));

        SubjectTeacher subjectTeacher = SubjectTeacher.builder()
                .subject(subject)
                .teacher(teacher)
                .build();

        subjectTeacherRepository.save(subjectTeacher);

        return teacher;
    }

}
