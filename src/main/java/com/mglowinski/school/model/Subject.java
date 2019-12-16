package com.mglowinski.school.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@AllArgsConstructor
public class Subject implements Serializable {

    private static final long serialVersionUID = 6594299910198589846L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull(message = "Subject name must be provided")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private School school;
}
