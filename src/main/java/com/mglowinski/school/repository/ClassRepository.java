package com.mglowinski.school.repository;

import com.mglowinski.school.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {

    List<Class> findAllBySchoolId(Long schoolId);

    Optional<Class> findByIdAndSchoolId(Long id, Long schoolId);
}
