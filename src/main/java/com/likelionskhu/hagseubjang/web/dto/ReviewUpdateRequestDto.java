package com.likelionskhu.hagseubjang.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewUpdateRequestDto {
    private String title;
    private String content;
    private Integer grade;

    @Builder
    public ReviewUpdateRequestDto(String title, String content, Integer grade) {
        this.title = title;
        this.content = content;
        this.grade = grade;
    }
}
