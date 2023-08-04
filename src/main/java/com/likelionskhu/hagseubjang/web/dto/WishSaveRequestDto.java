package com.likelionskhu.hagseubjang.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WishSaveRequestDto {
    private int lectureId;
    private int userId;

    @Builder
    public WishSaveRequestDto(int lectureId, int userId) {
        this.lectureId = lectureId;
        this.userId = userId;
    }
}
