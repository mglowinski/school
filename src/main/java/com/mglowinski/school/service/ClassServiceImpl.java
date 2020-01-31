package com.mglowinski.school.service;

import com.mglowinski.school.dto.AssignedStudentDto;
import com.mglowinski.school.dto.AssignedSubjectWithTeacherDto;
import com.mglowinski.school.dto.AssignedTutorDto;
import com.mglowinski.school.model.Class;
import com.mglowinski.school.model.*;
import com.mglowinski.school.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    private final ClassRepository classRepository;
    private final SchoolRepository schoolRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final SubjectTeacherRepository subjectTeacherRepository;
    private final ClassSubjectTeacherRepository classSubjectTeacherRepository;

    @Autowired

    public ClassServiceImpl(ClassRepository classRepository,
                            SchoolRepository schoolRepository,
                            TeacherRepository teacherRepository,
                            StudentRepository studentRepository,
                            SubjectTeacherRepository subjectTeacherRepository,
                            ClassSubjectTeacherRepository classSubjectTeacherRepository) {
        this.classRepository = classRepository;
        this.schoolRepository = schoolRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.subjectTeacherRepository = subjectTeacherRepository;
        this.classSubjectTeacherRepository = classSubjectTeacherRepository;
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

    @Override
    public void assignTutor(Long schoolId, Long classId, AssignedTutorDto assignedTutorDto) {
        Teacher teacher = teacherRepository.findByIdAndSchoolId(assignedTutorDto.getId(), schoolId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Teacher not found with id: " + assignedTutorDto.getId()));

        classRepository.findByIdAndSchoolId(classId, schoolId)
                .map(schoolClass -> {
                    schoolClass.setTutor(teacher);
                    return classRepository.save(schoolClass);
                })
                .orElseThrow(() -> new EntityNotFoundException("Class not found with id: " + classId));
    }

    @Override
    public void assignSubjectWithTeacher(Long schoolId,
                                         Long classId,
                                         AssignedSubjectWithTeacherDto assignedSubjectWithTeacherDto) {
        SubjectTeacher subjectTeacher
                = subjectTeacherRepository.findBySubjectIdAndTeacherId(
                assignedSubjectWithTeacherDto.getSubjectId(),
                assignedSubjectWithTeacherDto.getTeacherId())
                .orElseThrow(() -> new EntityNotFoundException("Subject-Teacher not found"));

        Class schoolClass = classRepository.findByIdAndSchoolId(classId, schoolId)
                .orElseThrow(() -> new EntityNotFoundException("Class not found with id: " + classId));

        ClassSubjectTeacher classSubjectTeacher =
                new ClassSubjectTeacher(schoolClass, subjectTeacher);

        classSubjectTeacherRepository.save(classSubjectTeacher);
    }

    @Override
    public Class getClassBySchoolIdAndClassId(Long schoolId, Long classId) {
        return classRepository.findByIdAndSchoolId(classId, schoolId)
                .orElseThrow(() -> new EntityNotFoundException("Class not found with id: " + classId));
    }

    @Override
    public List<Student> getAllStudentsFromClass(Long schoolId, Long classId) {
        return studentRepository.findAllBySchoolIdAndSchoolClassId(schoolId, classId);
    }

    @Override
    public void addStudentToClass(Long schoolId,
                                  Long classId,
                                  AssignedStudentDto assignedStudentDto) {
        Class schoolClass = classRepository.findByIdAndSchoolId(classId, schoolId)
                .orElseThrow(() -> new EntityNotFoundException("Class not found with id: " + classId));

        Student student = studentRepository.findByIdAndSchoolId(assignedStudentDto.getId(), schoolId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + assignedStudentDto.getId()));

        student.setSchoolClass(schoolClass);
        studentRepository.save(student);
    }

}
