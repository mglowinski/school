package com.mglowinski.school.service;

import com.mglowinski.school.dto.AssignedSubjectDto;
import com.mglowinski.school.model.Subject;
import com.mglowinski.school.model.Teacher;
import com.mglowinski.school.repository.SchoolRepository;
import com.mglowinski.school.repository.SubjectRepository;
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

    @Autowired
    public TeacherServiceImpl(SchoolRepository schoolRepository,
                              TeacherRepository teacherRepository,
                              SubjectRepository subjectRepository) {
        this.schoolRepository = schoolRepository;
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
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

        return teacherRepository.findByIdAndSchoolId(teacherId, schoolId)
                .map(teacher -> {
                    teacher.getSubjects().add(subject);
                    subject.getTeachers().add(teacher);
                    return teacherRepository.save(teacher);
                })
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found with id: " + teacherId));
    }

}
