package com.tuition.service.impl;

import com.tuition.dto.VideoRequestDto;
import com.tuition.entity.Video;

import java.util.List;

public interface VideoServices {

    Video createVideo(VideoRequestDto dto);
    List<Video> getVideosByChapter(Long ChapterId);
}
