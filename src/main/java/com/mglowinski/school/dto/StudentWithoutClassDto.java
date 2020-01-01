package com.mglowinski.school.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentWithoutClassDto implements Serializable {

    private static final long serialVersionUID = 6076058090762507510L;

    private Long id;

    private String firstName;

    private String lastName;

    private AddressDto address;
}
