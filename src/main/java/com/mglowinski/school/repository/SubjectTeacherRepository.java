package com.mglowinski.school.repository;

import com.mglowinski.school.dto.SubjectDto;
import com.mglowinski.school.model.SubjectTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectTeacherRepository extends JpaRepository<SubjectTeacher, Long> {

    @Query("SELECT new com.mglowinski.school.dto.SubjectDto(st.id, st.subject.name) " +
            "FROM SubjectTeacher st " +
            "WHERE st.teacher.id = :teacherId")
    List<SubjectDto> retrieveSubjectsByTeacherId(Long teacherId);
}
