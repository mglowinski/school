package com.mglowinski.school.utils;

import com.mglowinski.school.dto.*;
import com.mglowinski.school.model.Class;
import com.mglowinski.school.model.*;
import com.mglowinski.school.repository.SubjectTeacherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ObjectMapper {

    private final ModelMapper modelMapper;
    private final SubjectTeacherRepository subjectTeacherRepository;

    @Autowired
    public ObjectMapper(ModelMapper modelMapper,
                        SubjectTeacherRepository subjectTeacherRepository) {
        this.modelMapper = modelMapper;
        this.subjectTeacherRepository = subjectTeacherRepository;
    }

    public School mapCreateSchoolDtoToEntity(CreateSchoolDto createSchoolDto) {
        return modelMapper.map(createSchoolDto, School.class);
    }

    public SchoolDto mapSchoolEntityToDto(School school) {
        return modelMapper.map(school, SchoolDto.class);
    }

    public Subject mapCreateSubjectDtoToEntity(CreateSubjectDto createSubjectDto) {
        return modelMapper.map(createSubjectDto, Subject.class);
    }

    public SubjectDto mapSubjectEntityToDto(Subject subject) {
        return modelMapper.map(subject, SubjectDto.class);
    }

    public ClassDto mapClassEntityToDto(Class schoolClass) {
        return modelMapper.map(schoolClass, ClassDto.class);
    }

    public Class mapClassDtoToEntity(ClassDto classDto) {
        return modelMapper.map(classDto, Class.class);
    }

    public TeacherDto mapTeacherEntityToDto(Teacher teacher) {
       /* List<SubjectDto> subjects =
                subjectTeacherRepository.retrieveSubjectsByTeacherId(teacher.getId());
        TeacherDto teacherDto = modelMapper.map(teacher, TeacherDto.class);
        teacherDto.setSubjects(subjects);*/

        List<Subject> subjects = teacher.getSubjectTeachers()
                .stream()
                .map(SubjectTeacher::getSubject)
                .collect(Collectors.toList());

        List<SubjectDto> subjectsDto = subjects.stream()
                .map(this::mapSubjectEntityToDto)
                .collect(Collectors.toList());

        TeacherDto teacherDto = modelMapper.map(teacher, TeacherDto.class);
        teacherDto.setSubjects(subjectsDto);

        return teacherDto;
    }

    public Teacher mapCreateTeacherDtoToEntity(CreateTeacherDto createTeacherDto) {
        return modelMapper.map(createTeacherDto, Teacher.class);
    }

    public StudentDto mapStudentEntityToStudentDto(Student student) {
        return modelMapper.map(student, StudentDto.class);
    }

    public Student mapCreateStudentDtoToEntity(CreateStudentDto createStudentDto) {
        return modelMapper.map(createStudentDto, Student.class);
    }

    public StudentWithoutClassDto mapStudentEntityToStudentWithoutClassDto(Student student) {
        return modelMapper.map(student, StudentWithoutClassDto.class);
    }

    public IssuedGradeDto mapIssuedGradeEntityToIssuedGradeDto(IssuedGrade issuedGrade) {
        return modelMapper.map(issuedGrade, IssuedGradeDto.class);
    }

}
