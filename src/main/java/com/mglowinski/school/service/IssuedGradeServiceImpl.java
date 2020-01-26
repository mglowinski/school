package com.mglowinski.school.service;

import com.mglowinski.school.dto.CreateIssuedGradeDto;
import com.mglowinski.school.model.Class;
import com.mglowinski.school.model.*;
import com.mglowinski.school.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class IssuedGradeServiceImpl implements IssuedGradeService {

    private final IssuedGradeRepository issuedGradeRepository;
    private final ClassRepository classRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    private final RateRepository rateRepository;

    @Autowired
    public IssuedGradeServiceImpl(IssuedGradeRepository issuedGradeRepository,
                                  ClassRepository classRepository,
                                  StudentRepository studentRepository,
                                  SubjectRepository subjectRepository,
                                  TeacherRepository teacherRepository,
                                  RateRepository rateRepository) {
        this.issuedGradeRepository = issuedGradeRepository;
        this.classRepository = classRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
        this.rateRepository = rateRepository;
    }

    @Override
    public List<IssuedGrade> getIssuedGradesByClassId(Long classId) {
        return issuedGradeRepository.findAllBySchoolClassId(classId);
    }

    @Override
    public void createIssuedGrade(Long schoolId,
                                  Long classId,
                                  CreateIssuedGradeDto createIssuedGradeDto) {
        Class schoolClass = classRepository.findByIdAndSchoolId(classId, schoolId)
                .orElseThrow(() -> new EntityNotFoundException("Class not found with id: " + classId));

        Student student = studentRepository.findByIdAndSchoolId(createIssuedGradeDto.getStudentId(), schoolId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + createIssuedGradeDto.getStudentId()));

        Subject subject = subjectRepository.findByIdAndSchoolId(createIssuedGradeDto.getSubjectId(), schoolId)
                .orElseThrow(() -> new EntityNotFoundException("Subject not found with id: " + createIssuedGradeDto.getSubjectId()));

        Teacher teacher = teacherRepository.findByIdAndSchoolId(createIssuedGradeDto.getTeacherId(), schoolId)
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found with id: " + createIssuedGradeDto.getTeacherId()));

        Rate rate = rateRepository.findByIdAndSchoolId(createIssuedGradeDto.getRateId(), schoolId)
                .orElseThrow(() -> new EntityNotFoundException("Rate not found with id: " + createIssuedGradeDto.getRateId()));

        IssuedGrade issuedGrade = IssuedGrade.builder()
                .schoolClass(schoolClass)
                .student(student)
                .subject(subject)
                .teacher(teacher)
                .rate(rate)
                .build();

        issuedGradeRepository.save(issuedGrade);
    }

}
