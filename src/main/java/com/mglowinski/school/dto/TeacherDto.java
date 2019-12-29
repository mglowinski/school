package com.mglowinski.school.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDto implements Serializable {

    private static final long serialVersionUID = 3662233273296427789L;

    private Long id;

    @NotNull(message = "Teacher firstName must be provided")
    private String firstName;

    @NotNull(message = "Teacher lastName must be provided")
    private String lastName;

    @NotNull(message = "Teacher phoneNumber must be provided")
    private String phoneNumber;

    @NotNull(message = "Teacher email must be provided")
    private String email;

    private List<SubjectDto> subjects;
}
