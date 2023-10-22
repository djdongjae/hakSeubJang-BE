package com.likelionskhu.hagseubjang.domain.lecture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Integer> {

    List<Lecture> findByEdcRdnmadrLikeOrderByRceptEndDate(String edcRdnmadr);

    List<Lecture> findByLctreNmLikeOrderByRceptEndDate(String lctreNm);

    @Query("""
            select l
            from Lecture l left join l.wishes w
            group by l
            order by count(w) desc
            """)
    List<Lecture> findByWishCount();

}
