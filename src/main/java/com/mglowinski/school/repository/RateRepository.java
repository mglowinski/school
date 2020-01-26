package com.mglowinski.school.repository;

import com.mglowinski.school.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {

    Optional<Rate> findByIdAndSchoolId(Long id, Long schoolId);
}
