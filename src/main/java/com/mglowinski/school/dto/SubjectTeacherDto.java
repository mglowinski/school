package com.mglowinski.school.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectTeacherDto implements Serializable {

    private static final long serialVersionUID = 7714406591621055778L;

    private SubjectDto subject;
    private TeacherWithoutSubjectsDto teacher;
}
