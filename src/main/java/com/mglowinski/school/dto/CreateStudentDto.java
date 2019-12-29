package com.mglowinski.school.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentDto implements Serializable {

    private static final long serialVersionUID = -8127076575675033570L;

    @NotNull(message = "Student firstName must be provided")
    private String firstName;

    @NotNull(message = "Student lastName must be provided")
    private String lastName;

    @NotNull(message = "Address must be provided")
    @Valid
    private AddressDto address;
}
