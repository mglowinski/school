package com.mglowinski.school.repository;

import com.mglowinski.school.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllBySchoolId(Long schoolId);

    List<Student> findAllBySchoolIdAndSchoolClassId(Long schoolId, Long schoolClassId);
}
