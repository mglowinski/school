package com.mglowinski.school.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    @JoinColumn
    private School school;

    @OneToOne(mappedBy = "tutor", fetch = FetchType.LAZY)
    private Class schoolClass;
}
