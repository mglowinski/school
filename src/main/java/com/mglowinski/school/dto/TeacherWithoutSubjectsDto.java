package com.mglowinski.school.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherWithoutSubjectsDto implements Serializable {

    private static final long serialVersionUID = 3662233273296427783L;

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
}
