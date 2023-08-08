package com.likelionskhu.hagseubjang.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewSaveRequestDto {

    private String title;
    private String content;
    private int lectureId;
    private String userEmail;

    @Builder
    public ReviewSaveRequestDto(String title, String content, int lectureId, String userEmail) {
        this.title = title;
        this.content = content;
        this.lectureId = lectureId;
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "ReviewSaveRequestDto{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", lectureId=" + lectureId +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
