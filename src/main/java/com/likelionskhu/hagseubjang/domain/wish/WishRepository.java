package com.likelionskhu.hagseubjang.domain.wish;

import com.likelionskhu.hagseubjang.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishRepository extends JpaRepository<Wish, Integer> {
    boolean existsByUser(User user);
}
