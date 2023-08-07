package com.likelionskhu.hagseubjang.service;

import com.likelionskhu.hagseubjang.config.auth.dto.SessionUser;
import com.likelionskhu.hagseubjang.domain.review.Review;
import com.likelionskhu.hagseubjang.domain.user.User;
import com.likelionskhu.hagseubjang.domain.user.UserRepository;
import com.likelionskhu.hagseubjang.domain.wish.Wish;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Transactional
    public List<Review> findReviews() {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            User user1 = userRepository.findByEmail(user.getEmail())
                    .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. email=" + user.getEmail()));

            List<Review> reviews = user1.getReviews();

            return reviews;
        }
        return null;
    }

    @Transactional
    public List<Wish> findWishes() {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            User user1 = userRepository.findByEmail(user.getEmail())
                    .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. email=" + user.getEmail()));

            List<Wish> wishes = user1.getWishes();

            return wishes;
        }
        return null;
    }
}
