package com.mglowinski.school.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolDto implements Serializable {

    private static final long serialVersionUID = -5275949272013399867L;

    private Long id;

    @NotNull(message = "School name must be provided")
    private String name;

    @NotNull(message = "Address must be provided")
    @Valid
    private AddressDto address;

    private List<SubjectDto> subjects;

    private List<ClassDto> classes;

    private List<TeacherDto> teachers;
}
