package com.mglowinski.school.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto implements Serializable {

    private static final long serialVersionUID = -7320338434282350125L;

    private Long id;

    private String firstName;

    private String lastName;

    private AddressDto address;

    private StudentClassDto schoolClass;
}
