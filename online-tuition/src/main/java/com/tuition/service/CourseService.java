package com.tuition.service;

import com.tuition.dto.CourseRequestDto;
import com.tuition.entity.Course;
import com.tuition.repository.CourseRepository;
import com.tuition.service.impl.CourseServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CourseService  implements CourseServices {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course createCourse(CourseRequestDto dto) {
        log.info("Creating course for class {}" , dto.getClassLevel());
        Course course = new Course();
        course.setClassLevel(dto.getClassLevel());
        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());
        Course saved = courseRepository.save(course);
        log.info("Course created with id {} ", saved.getId());
        return saved;
    }

    @Override
    public List<Course> getAllCourses() {
        log.info("fetching all courses ");
        return courseRepository.findAll();
    }
}
