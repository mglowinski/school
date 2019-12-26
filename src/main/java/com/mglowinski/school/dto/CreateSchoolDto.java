package com.mglowinski.school.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateSchoolDto implements Serializable {

    private static final long serialVersionUID = -6435011444804184661L;

    private Long id;

    @NotNull(message = "School name must be provided")
    private String name;

    @NotNull(message = "Address must be provided")
    @Valid
    private AddressDto address;
}
