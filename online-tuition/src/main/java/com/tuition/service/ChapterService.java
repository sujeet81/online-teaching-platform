package com.tuition.service;

import com.tuition.dto.ChapterRequestDto;
import com.tuition.dto.VideoRequestDto;
import com.tuition.entity.Chapter;
import com.tuition.entity.Subject;
import com.tuition.exception.ResourceNotFoundException;
import com.tuition.repository.ChapterRepository;
import com.tuition.repository.SubjectRepository;
import com.tuition.service.impl.ChapterServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ChapterService implements ChapterServices {

    private final ChapterRepository chapterRepository;
    private final SubjectRepository subjectRepository;

    public ChapterService(ChapterRepository chapterRepository, SubjectRepository subjectRepository) {
        this.chapterRepository = chapterRepository;
        this.subjectRepository = subjectRepository;
    }


    @Override
    public Chapter createChapter(ChapterRequestDto dto) {
        log.info("Creating chapter: {} " , dto.getName());

        Subject subject = subjectRepository.findById(dto.getSubjectId())
                .orElseThrow(() -> {
                    log.error("Subject not found with id {}", dto.getSubjectId());
                    return new ResourceNotFoundException("Subject not found ");
                });
        Chapter chapter = new Chapter();
        chapter.setName(dto.getName());
        chapter.setSubject(subject);

       Chapter saved = chapterRepository.save(chapter);
       log.info("Chapter created successfully with id {} ", saved.getId());
       return saved;

    }

    @Override
    public List<Chapter> getChaptersBySubject(Long subjectId) {
        log.info("Fetching chapters for subject id {} ", subjectId);
        return chapterRepository.findBySubjectId(subjectId);
    }
}
