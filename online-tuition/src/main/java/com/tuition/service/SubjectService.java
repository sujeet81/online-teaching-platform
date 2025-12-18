package com.tuition.service;

import com.tuition.dto.SubjectRequestDto;
import com.tuition.entity.Course;
import com.tuition.entity.Subject;
import com.tuition.exception.ResourceNotFoundException;
import com.tuition.repository.CourseRepository;
import com.tuition.repository.SubjectRepository;
import com.tuition.service.impl.SubjectServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SubjectService implements SubjectServices {

    private final SubjectRepository subjectRepository;
    private final CourseRepository courseRepository;

    public SubjectService(SubjectRepository subjectRepository, CourseRepository courseRepository) {
        this.subjectRepository = subjectRepository;
        this.courseRepository = courseRepository;
    }


    @Override
    public Subject createSubject(SubjectRequestDto dto) {
        log.info("Creating subject {} ", dto.getName());

        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found "));

        Subject subject = new Subject();
        subject.setName(dto.getName());
        subject.setCourse(course);

        Subject saved = subjectRepository.save(subject);
        log.info("Subject created with id {}", saved.getId());
        return saved;
    }

    @Override
    public List<Subject> getSubjectsByCourse(Long courseId) {
        log.info("Fetching subjects for course {} ", courseId);
        return subjectRepository.findByCourseId(courseId);
    }
}
