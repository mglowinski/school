package com.mglowinski.school.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
public class ErrorDto {

    private HttpStatus error;
    private String message;
    private List<String> details;
}
