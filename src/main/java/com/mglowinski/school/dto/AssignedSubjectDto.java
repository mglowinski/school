package com.mglowinski.school.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignedSubjectDto implements Serializable {

    private static final long serialVersionUID = -7920027147441933881L;

    private Long id;
}
