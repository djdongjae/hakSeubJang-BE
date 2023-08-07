package com.likelionskhu.hagseubjang.web.dto;

import com.likelionskhu.hagseubjang.domain.lecture.Lecture;
import com.likelionskhu.hagseubjang.domain.review.Review;
import com.likelionskhu.hagseubjang.domain.review.ReviewRepository;
import com.likelionskhu.hagseubjang.domain.user.User;
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
    private Lecture lecture;
    private User user;

    @Builder
    public ReviewSaveRequestDto(String title, String content, Lecture lecture, User user) {
        this.title = title;
        this.content = content;
        this.lecture = lecture;
        this.user = user;
    }

    public Review toEntity() {
        return Review.builder()
                .title(title)
                .content(content)
                .lecture(lecture)
                .user(user)
                .build();
    }
}
