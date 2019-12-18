package com.mglowinski.school.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto implements Serializable {

    private static final long serialVersionUID = -8428573639397469976L;

    @NotNull(message = "School address street must be provided")
    private String street;

    @NotNull(message = "School address city must be provided")
    private String city;

    @NotNull(message = "School address zip-code must be provided")
    private String zipCode;
}
