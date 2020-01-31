package com.mglowinski.school.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignedSubjectWithTeacherDto implements Serializable {

    private static final long serialVersionUID = 7312939174580817201L;

    private Long subjectId;
    private Long teacherId;
}