package com.tuition.controller;

import com.tuition.dto.SubjectRequestDto;
import com.tuition.entity.Subject;
import com.tuition.service.SubjectService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
@Slf4j
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @PostMapping
    public ResponseEntity<Subject> create(@RequestBody @Valid SubjectRequestDto dto){
        log.info("API Call : Create subject ");
        return ResponseEntity.ok(subjectService.createSubject(dto));
    }

    public ResponseEntity<List<Subject>> getByCourse(@PathVariable Long courseId){
        log.info("API Call : get subjects by course ");
        return ResponseEntity.ok(subjectService.getSubjectsByCourse(courseId));
    }
}
