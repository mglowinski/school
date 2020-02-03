package com.mglowinski.school.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssuedGradeDto implements Serializable {

    private static final long serialVersionUID = 357280077607086057L;

    private Long id;
    private Date date;
    private StudentWithoutClassDto student;
    private SubjectDto subject;
    private RateDto rate;
}
