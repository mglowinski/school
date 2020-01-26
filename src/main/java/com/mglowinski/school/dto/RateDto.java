package com.mglowinski.school.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RateDto implements Serializable {

    private static final long serialVersionUID = 6074041352194518942L;

    private Long id;

    private String fullName;

    private int value;
}
