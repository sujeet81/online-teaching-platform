package com.tuition.service.impl;


import com.tuition.dto.CourseRequestDto;
import com.tuition.entity.Course;

import java.util.List;

public interface CourseServices {

    Course createCourse(CourseRequestDto dto);
    List<Course> getAllCourses();

}
