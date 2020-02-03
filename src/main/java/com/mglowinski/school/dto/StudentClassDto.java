package com.mglowinski.school.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentClassDto implements Serializable {

    private static final long serialVersionUID = -5536105221079990765L;

    private Long id;
    private String level;
    private String name;
    private String specialization;
}
