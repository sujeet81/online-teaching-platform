package com.tuition.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoRequestDto {

    private String title;
    private String youtubeUrl;
    private Long chapterId;

}
