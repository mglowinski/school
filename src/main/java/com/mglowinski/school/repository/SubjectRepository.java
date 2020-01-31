package com.mglowinski.school.repository;

import com.mglowinski.school.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Query("SELECT s " +
            "FROM Subject s " +
            "LEFT JOIN FETCH s.subjectTeachers " +
            "WHERE s.school.id = :schoolId")
    List<Subject> findAllBySchoolId(Long schoolId);

    Optional<Subject> findByIdAndSchoolId(Long id, Long schoolId);
}
