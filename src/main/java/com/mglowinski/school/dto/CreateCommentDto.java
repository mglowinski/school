package com.mglowinski.school.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentDto implements Serializable {

    private static final long serialVersionUID = -5546731634725518318L;

    private String note;
}
