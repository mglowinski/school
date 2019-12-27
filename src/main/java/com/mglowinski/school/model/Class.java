package com.mglowinski.school.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String level;

    private String name;

    private String specialization;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private School school;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Teacher tutor;
}
