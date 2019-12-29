package com.mglowinski.school.utils;

import com.mglowinski.school.dto.*;
import com.mglowinski.school.model.Class;
import com.mglowinski.school.model.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ObjectMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public ObjectMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
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
        return modelMapper.map(teacher, TeacherDto.class);
    }

    public Teacher mapCreateTeacherDtoToEntity(CreateTeacherDto createTeacherDto) {
        return modelMapper.map(createTeacherDto, Teacher.class);
    }

    public StudentDto mapStudentEntityToDto(Student student) {
        return modelMapper.map(student, StudentDto.class);
    }

    public Student mapCreateStudentDtoToEntity(CreateStudentDto createStudentDto) {
        return modelMapper.map(createStudentDto, Student.class);
    }

}
