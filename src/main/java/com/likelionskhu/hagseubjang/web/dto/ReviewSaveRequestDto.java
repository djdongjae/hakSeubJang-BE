package com.likelionskhu.hagseubjang.web.dto;

import com.likelionskhu.hagseubjang.domain.review.Review;
import com.likelionskhu.hagseubjang.domain.review.ReviewRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@NoArgsConstructor
public class ReviewSaveRequestDto {

    private String title;
    private String content;
    private int lectureId;
    private int userId;

    @Builder
    public ReviewSaveRequestDto(String title, String content, int lectureId, int userId) {
        this.title = title;
        this.content = content;
        this.lectureId = lectureId;
        this.userId = userId;
    }

    public Review toEntity() {
        return Review.builder()
                .title(title)
                .content(content)
                .build();
    }
}
