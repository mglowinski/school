package com.mglowinski.school.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDto implements Serializable {

    private static final long serialVersionUID = -3161756096041797802L;

    private Long id;
    private String name;
    private SchoolDto school;
}
