package com.mglowinski.school.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassSubjectTeacherDto implements Serializable {

    private static final long serialVersionUID = -8984043556747397066L;

    private Long id;
    private String level;
    private String name;
    private String specialization;
    private TutorDto tutor;
    private List<SubjectTeacherDto> subjectsTeachers;
}
