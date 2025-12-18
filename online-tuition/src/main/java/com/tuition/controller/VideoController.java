package com.tuition.controller;

import com.tuition.dto.VideoRequestDto;
import com.tuition.entity.Video;
import com.tuition.service.VideoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videos")
@Slf4j
public class VideoController {

    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping
    public ResponseEntity<Video> create(@RequestBody @Valid VideoRequestDto dto){
        log.info("API call : Create Video ");
        return ResponseEntity.ok(videoService.createVideo(dto));
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/chapter/{chapterId}")
    public ResponseEntity<List<Video>> getByChapter(@PathVariable Long chapterId){
        log.info("API Call : get videos by Chapter ");
        return ResponseEntity.ok(videoService.getVideosByChapter(chapterId));
    }
}
