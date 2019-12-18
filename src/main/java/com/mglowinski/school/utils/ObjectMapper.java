package com.mglowinski.school.utils;

import com.mglowinski.school.dto.CreateSubjectDto;
import com.mglowinski.school.dto.SchoolDto;
import com.mglowinski.school.dto.SubjectDto;
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

    public School mapSchoolDtoToEntity(SchoolDto schoolDto) {
        School school = modelMapper.map(schoolDto, School.class);
        school.setSubjects(Collections.emptyList());
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
}
