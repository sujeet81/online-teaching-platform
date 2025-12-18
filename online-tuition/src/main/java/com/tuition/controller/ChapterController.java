package com.tuition.controller;

import com.tuition.dto.ChapterRequestDto;
import com.tuition.entity.Chapter;
import com.tuition.service.ChapterService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chapters")
@Slf4j
public class ChapterController {

    private final ChapterService chapterService;

    public ChapterController(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping
    public ResponseEntity<Chapter> create(@RequestBody @Valid ChapterRequestDto dto){
        log.info("API Call : Create Chapter " );
        return ResponseEntity.ok(chapterService.createChapter(dto));
    }

    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<Chapter>> getBySubject(@PathVariable Long subjectId){
        log.info("API Call: get chapter by subject ");
        return ResponseEntity.ok(chapterService.getChaptersBySubject(subjectId));
    }
}
