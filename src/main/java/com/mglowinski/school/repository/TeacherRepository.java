package com.mglowinski.school.repository;

import com.mglowinski.school.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query("SELECT t " +
            "FROM Teacher t " +
            "LEFT JOIN FETCH t.subjectTeachers st " +
            "where t.school.id = :schoolId")
    List<Teacher> findAllBySchoolId(Long schoolId);

    Optional<Teacher> findByIdAndSchoolId(Long id, Long schoolId);
}
