package com.likelionskhu.hagseubjang.domain.wish;

import com.likelionskhu.hagseubjang.config.auth.dto.SessionUser;
import com.likelionskhu.hagseubjang.domain.lecture.Lecture;
import com.likelionskhu.hagseubjang.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "lectureId")
    private Lecture lecture;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Override
    public boolean equals(Object object) {
        SessionUser user = (SessionUser) object;

        if (this.user.getEmail().equals(user.getEmail())) {
            return true;
        } else {
            return false;
        }
    }

}
