package com.mglowinski.school.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateIssuedGradeDto implements Serializable {

    private static final long serialVersionUID = 1887356175157030481L;

    @NotNull(message = "Student ID must be provided")
    private Long studentId;

    @NotNull(message = "Subject ID must be provided")
    private Long subjectId;

    @NotNull(message = "Teacher ID must be provided")
    private Long teacherId;

    @NotNull(message = "Rate ID must be provided")
    private Long rateId;
}
