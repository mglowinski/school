package com.mglowinski.school.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDto implements Serializable {

    private static final long serialVersionUID = 3662233273296427789L;

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private List<SubjectDto> subjects;
}
