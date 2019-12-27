package com.mglowinski.school.service;

import com.mglowinski.school.dto.AssignedTutorDto;
import com.mglowinski.school.model.Class;
import com.mglowinski.school.model.Teacher;
import com.mglowinski.school.repository.ClassRepository;
import com.mglowinski.school.repository.SchoolRepository;
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

    @Autowired
    public ClassServiceImpl(ClassRepository classRepository,
                            SchoolRepository schoolRepository,
                            TeacherRepository teacherRepository) {
        this.classRepository = classRepository;
        this.schoolRepository = schoolRepository;
        this.teacherRepository = teacherRepository;
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

}
