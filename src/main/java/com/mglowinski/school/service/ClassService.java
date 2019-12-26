package com.mglowinski.school.service;

import com.mglowinski.school.model.Class;

import java.util.List;

public interface ClassService {

    List<Class> getAllClassesBySchoolId(Long schoolId);

    Class createClass(Long schoolId, Class schoolClass);
}