package com.likelionskhu.hagseubjang.domain.review;

import com.likelionskhu.hagseubjang.domain.BaseTimeEntity;
import com.likelionskhu.hagseubjang.domain.lecture.Lecture;
import com.likelionskhu.hagseubjang.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

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

    @Column
    private int grade;

    @ManyToOne
    @JoinColumn(name = "lectureId")
    Lecture lecture;

    @ManyToOne
    @JoinColumn(name = "userId")
    User user;

    @Builder
    public Review(String title, String content, int grade, Lecture lecture, User user) {
        this.title = title;
        this.content = content;
        this.grade = grade;
        this.lecture = lecture;
        this.user = user;
    }

    public void update(String title, String content, int grade) {
        this.title = title;
        this.content = content;
        this.grade = grade;
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
