package com.mglowinski.school.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignedStudentDto implements Serializable {

    private static final long serialVersionUID = 2541597178759696432L;

    private Long id;
}
