package com.mglowinski.school.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto implements Serializable {

    private static final long serialVersionUID = -7101448255561832357L;

    private Long id;
    private String note;
    private Date date;
    private TeacherWithoutSubjectsDto teacher;
}
