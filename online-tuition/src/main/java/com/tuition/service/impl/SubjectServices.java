package com.tuition.service.impl;

import com.tuition.dto.SubjectRequestDto;
import com.tuition.entity.Subject;

import java.util.List;

public interface SubjectServices {

    Subject createSubject(SubjectRequestDto dto);
    List<Subject>  getSubjectsByCourse(Long courseId);
}
