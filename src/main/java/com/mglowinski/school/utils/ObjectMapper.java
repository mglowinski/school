package com.mglowinski.school.utils;

import com.mglowinski.school.dto.SchoolDto;
import com.mglowinski.school.model.School;
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

}
