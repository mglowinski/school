package com.mglowinski.school.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "schoolClass")
    private List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "schoolClass")
    private List<IssuedGrade> issuedGrades = new ArrayList<>();

    @OneToMany(mappedBy = "schoolClass")
    private List<ClassSubjectTeacher> classSubjectsTeachers = new ArrayList<>();
}
