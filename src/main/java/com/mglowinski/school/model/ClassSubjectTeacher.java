package com.mglowinski.school.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
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

    public ClassSubjectTeacher(Class schoolClass, SubjectTeacher subjectTeacher) {
        this.schoolClass = schoolClass;
        this.subjectTeacher = subjectTeacher;
    }

}
