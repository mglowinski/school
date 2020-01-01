package com.mglowinski.school.service;

import com.mglowinski.school.dto.AssignedStudentDto;
import com.mglowinski.school.dto.AssignedTutorDto;
import com.mglowinski.school.model.Class;
import com.mglowinski.school.model.Student;
import com.mglowinski.school.model.Teacher;
import com.mglowinski.school.repository.ClassRepository;
import com.mglowinski.school.repository.SchoolRepository;
import com.mglowinski.school.repository.StudentRepository;
import com.mglowinski.school.repository.TeacherRepository;
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

    @Autowired
    public ClassServiceImpl(ClassRepository classRepository,
                            SchoolRepository schoolRepository,
                            TeacherRepository teacherRepository,
                            StudentRepository studentRepository) {
        this.classRepository = classRepository;
        this.schoolRepository = schoolRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
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
