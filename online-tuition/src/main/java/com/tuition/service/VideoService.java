package com.tuition.service;

import com.tuition.dto.VideoRequestDto;
import com.tuition.entity.Chapter;
import com.tuition.entity.Video;
import com.tuition.exception.ResourceNotFoundException;
import com.tuition.repository.ChapterRepository;
import com.tuition.repository.VideoRepository;
import com.tuition.service.impl.VideoServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class VideoService implements VideoServices {

    private final VideoRepository videoRepository;
    private final ChapterRepository chapterRepository;

    public VideoService(VideoRepository videoRepository, ChapterRepository chapterRepository) {
        this.videoRepository = videoRepository;
        this.chapterRepository = chapterRepository;
    }

    @Override
    public Video createVideo(VideoRequestDto dto) {
        log.info("Creating Video :{} ", dto.getTitle());
        Chapter chapter = chapterRepository.findById(dto.getChapterId())
                .orElseThrow(() -> {
                    log.error("Chapter not found with id {} ", dto.getChapterId());
                    return new ResourceNotFoundException("Chapter not found ");
                });
        Video video = new Video();
        video.setTitle(dto.getTitle());
        video.setYoutubeUrl(dto.getYoutubeUrl());
        video.setChapter(chapter);

        Video saved = videoRepository.save(video);
        log.info("Video Created Successfully with id {} ", saved.getId());
        return saved;
    }

    @Override
    public List<Video> getVideosByChapter(Long chapterId) {
        log.info("Fetching videos for chapter id {}", chapterId);
        return videoRepository.findByChapterId(chapterId);
    }
}
