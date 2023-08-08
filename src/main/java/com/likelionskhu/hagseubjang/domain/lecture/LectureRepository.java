package com.likelionskhu.hagseubjang.domain.lecture;

import com.likelionskhu.hagseubjang.model.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Integer> {

//    public default List<Lecture> findAll(Pagination pagination) {
//        Page<Lecture> page = this.findAll(PageRequest.of(pagination.getPage() - 1, pagination.getSize(),
//                Sort.Direction.ASC, "rceptEndDate"));
//        pagination.setRecordCount((int) page.getTotalElements());
//        return page.getContent();
//    }
}
