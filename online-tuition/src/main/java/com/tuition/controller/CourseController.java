package com.tuition.controller;

import com.tuition.dto.CourseRequestDto;
import com.tuition.entity.Course;
import com.tuition.service.ChapterService;
import com.tuition.service.CourseService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@Slf4j
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Course> create(@RequestBody @Valid CourseRequestDto dto){
        log.info("API Call : Create Course");
        return ResponseEntity.ok(courseService.createCourse(dto));
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAll(){
        log.info("API Call : get all course");

        return ResponseEntity.ok(courseService.getAllCourses());
    }
}
