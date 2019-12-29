package com.mglowinski.school.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTeacherDto implements Serializable {

    private static final long serialVersionUID = -3417056965669018976L;

    @NotNull(message = "Teacher firstName must be provided")
    private String firstName;

    @NotNull(message = "Teacher lastName must be provided")
    private String lastName;

    @NotNull(message = "Teacher phoneNumber must be provided")
    private String phoneNumber;

    @NotNull(message = "Teacher email must be provided")
    private String email;
}
