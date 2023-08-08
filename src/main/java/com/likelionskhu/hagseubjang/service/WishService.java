package com.likelionskhu.hagseubjang.service;

import com.likelionskhu.hagseubjang.config.auth.dto.SessionUser;
import com.likelionskhu.hagseubjang.domain.lecture.Lecture;
import com.likelionskhu.hagseubjang.domain.lecture.LectureRepository;
import com.likelionskhu.hagseubjang.domain.user.User;
import com.likelionskhu.hagseubjang.domain.user.UserRepository;
import com.likelionskhu.hagseubjang.domain.wish.Wish;
import com.likelionskhu.hagseubjang.domain.wish.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Service
public class WishService {

    private final WishRepository wishRepository;
    private final UserRepository userRepository;
    private final LectureRepository lectureRepository;

    private final HttpSession httpSession;

    @Transactional
    public void delete(int id) {
        Wish wish = wishRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 찜이 없습니다. id=" + id));

        wishRepository.delete(wish);
    }

    @Transactional
    public void saveOrDelete(int lectureId) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        User user1 = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. email=" + user.getEmail()));

        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new IllegalArgumentException("해당 강좌가 없습니다. id=" + lectureId));

        Wish wish = wishRepository.findByLectureAndUser(lecture, user1);

        if (wish == null) {
            wish = new Wish();
            wish.setLecture(lecture);
            wish.setUser(user1);
            wishRepository.save(wish);
        } else {
            wishRepository.delete(wish);
        }
    }

}
