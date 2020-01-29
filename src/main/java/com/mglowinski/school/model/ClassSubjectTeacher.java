package com.mglowinski.school.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@Table(name = "Class_Subject_Teacher")
@NoArgsConstructor
@AllArgsConstructor
public class ClassSubjectTeacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Class schoolClass;

    @ManyToOne
    private SubjectTeacher subjectTeacher;
}
