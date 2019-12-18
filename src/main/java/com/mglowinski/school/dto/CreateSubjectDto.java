package com.mglowinski.school.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateSubjectDto implements Serializable {

    private static final long serialVersionUID = 8351781728915085010L;

    @NotNull(message = "Subject name must be provided")
    private String name;
}
