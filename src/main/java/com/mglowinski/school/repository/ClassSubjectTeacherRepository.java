package com.mglowinski.school.repository;

import com.mglowinski.school.model.ClassSubjectTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassSubjectTeacherRepository extends JpaRepository<ClassSubjectTeacher, Long> {

}
