package com.mglowinski.school.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@Table(name = "Subject_Teacher")
@NoArgsConstructor
@AllArgsConstructor
public class SubjectTeacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Subject subject;

    @ManyToOne
    private Teacher teacher;

    @OneToMany(mappedBy = "subjectTeacher")
    private List<ClassSubjectTeacher> classSubjectTeacher;
}
