package com.mglowinski.school.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassDto implements Serializable {

    private static final long serialVersionUID = 7618998230044555622L;

    private Long id;

    @NotNull(message = "Class level must be provided")
    private String level;

    @NotNull(message = "Class name must be provided")
    private String name;

    private String specialization;
}
