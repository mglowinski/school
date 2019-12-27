package com.mglowinski.school.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignedTutorDto implements Serializable {

    private static final long serialVersionUID = -112467224522529095L;

    private Long id;
}
