package com.likelionskhu.hagseubjang.domain.lecture;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture, Integer> {

    List<Lecture> findByEdcRdnmadrLikeOrderByRceptEndDate(String edcRdnmadr);

    List<Lecture> findByLctreNmLikeOrderByRceptEndDate(String lctreNm);

}
