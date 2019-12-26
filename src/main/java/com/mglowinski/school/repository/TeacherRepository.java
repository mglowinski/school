package com.mglowinski.school.repository;

import com.mglowinski.school.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    List<Teacher> findAllBySchoolId(Long schoolId);

}
