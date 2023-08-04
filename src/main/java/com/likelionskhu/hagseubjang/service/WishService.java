package com.likelionskhu.hagseubjang.service;

import com.likelionskhu.hagseubjang.domain.lecture.Lecture;
import com.likelionskhu.hagseubjang.domain.lecture.LectureRepository;
import com.likelionskhu.hagseubjang.domain.user.User;
import com.likelionskhu.hagseubjang.domain.user.UserRepository;
import com.likelionskhu.hagseubjang.domain.wish.Wish;
import com.likelionskhu.hagseubjang.domain.wish.WishRepository;
import com.likelionskhu.hagseubjang.web.dto.WishSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class WishService {

    private WishRepository wishRepository;
    private UserRepository userRepository;
    private LectureRepository lectureRepository;

    @Transactional
    public void save(WishSaveRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. id=" + requestDto.getUserId()));

        Lecture lecture = lectureRepository.findById(requestDto.getLectureId())
                .orElseThrow(() -> new IllegalArgumentException("해당 강좌가 없습니다. id=" + requestDto.getLectureId()));

        Wish wish = new Wish();
        wish.setUser(user);
        wish.setLecture(lecture);
        wishRepository.save(wish);
    }

    @Transactional
    public void delete(int id) {
        Wish wish = wishRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 찜이 없습니다. id=" + id));

        wishRepository.delete(wish);
    }

}
