package com.tuition.service.impl;

import com.tuition.dto.ChapterRequestDto;
import com.tuition.entity.Chapter;

import java.util.List;

public interface ChapterServices {

    Chapter createChapter(ChapterRequestDto dto);
    List<Chapter> getChaptersBySubject(Long SubjectId);
}
