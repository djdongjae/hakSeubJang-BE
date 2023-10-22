package com.likelionskhu.hagseubjang.web.dto;

import com.likelionskhu.hagseubjang.domain.review.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewSaveRequestDto {

    private String title;
    private String content;
    private Integer grade;
    private Integer lectureId;

    @Builder
    public ReviewSaveRequestDto(String title, String content, Integer grade, Integer lectureId) {
        this.title = title;
        this.content = content;
        this.grade = grade == null ? 0 : grade;
        this.lectureId = lectureId;
    }

    public Review toEntity() {
        return Review.builder()
                .title(title)
                .content(content)
                .grade(grade)
                .build();
    }

}
