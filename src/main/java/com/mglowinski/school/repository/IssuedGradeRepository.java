package com.mglowinski.school.repository;

import com.mglowinski.school.model.IssuedGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssuedGradeRepository extends JpaRepository<IssuedGrade, Long> {

    List<IssuedGrade> findAllBySchoolClassId(Long classId);
}
