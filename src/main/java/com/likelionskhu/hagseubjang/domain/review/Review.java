package com.likelionskhu.hagseubjang.domain.review;

import com.likelionskhu.hagseubjang.domain.BaseTimeEntity;
import com.likelionskhu.hagseubjang.domain.lecture.Lecture;
import com.likelionskhu.hagseubjang.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String title;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "lectureId")
    Lecture lecture;

    @ManyToOne
    @JoinColumn(name = "userId")
    User user;

    @Builder
    public Review(String title, String content, Lecture lecture, User user) {
        this.title = title;
        this.content = content;
        this.lecture = lecture;
        this.user = user;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", lecture=" + lecture +
                ", user=" + user +
                '}';
    }
}
