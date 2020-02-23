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
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    private School school;

    @OneToOne(mappedBy = "tutor", fetch = FetchType.LAZY)
    private Class schoolClass;

    @OneToMany(mappedBy = "teacher")
    private List<SubjectTeacher> subjectTeachers = new ArrayList<>();

    @OneToMany(mappedBy = "teacher")
    private List<Comment> comments = new ArrayList<>();
}
