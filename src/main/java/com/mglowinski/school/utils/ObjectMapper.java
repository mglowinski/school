package com.mglowinski.school.utils;

import com.mglowinski.school.dto.*;
import com.mglowinski.school.model.Class;
import com.mglowinski.school.model.School;
import com.mglowinski.school.model.Subject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class ObjectMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public ObjectMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public School mapCreateSchoolDtoToEntity(CreateSchoolDto createSchoolDto) {
        School school = modelMapper.map(createSchoolDto, School.class);
        school.setSubjects(Collections.emptyList());
        school.setClasses(Collections.emptyList());
        return school;
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

}
