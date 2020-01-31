package com.mglowinski.school.repository;

import com.mglowinski.school.model.SubjectTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectTeacherRepository extends JpaRepository<SubjectTeacher, Long> {

    Optional<SubjectTeacher> findBySubjectIdAndTeacherId(Long subjectId, Long teacherId);
}
