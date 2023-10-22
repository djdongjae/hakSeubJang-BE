package com.likelionskhu.hagseubjang.web;

import com.likelionskhu.hagseubjang.domain.review.Review;
import com.likelionskhu.hagseubjang.service.ReviewService;
import com.likelionskhu.hagseubjang.web.dto.ReviewSaveRequestDto;
import com.likelionskhu.hagseubjang.web.dto.ReviewUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("review")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("create")
    public String create(Model model, @RequestParam("lectureId") int lectureId, @RequestParam("lctreNm") String lctreNm) {
        model.addAttribute("requestDto", reviewService.create(lectureId));
        model.addAttribute("lctreNm", lctreNm);
        return "review/create";
    }

    @PostMapping("create")
    public String create(Model model, ReviewSaveRequestDto requestDto) {
        reviewService.save(requestDto);
        return "redirect:/lecture/detail?id=" + requestDto.getLectureId();
    }

    @GetMapping("edit")
    public String edit(Model model, @RequestParam("reviewId") int reviewId) {
        Review review = reviewService.findById(reviewId);
        ReviewUpdateRequestDto requestDto = new ReviewUpdateRequestDto(review.getTitle(), review.getContent(), review.getGrade());
        model.addAttribute("lctreNm", review.getLecture().getLctreNm());
        model.addAttribute("lectureId", review.getLecture().getId());
        model.addAttribute("requestDto", requestDto);
        model.addAttribute("reviewId", review.getId());
        return "review/edit";
    }

    @PostMapping (value = "edit", params = "cmd=save")
    public String edit(Model model, @RequestParam("reviewId") int reviewId, ReviewUpdateRequestDto requestDto) {
        reviewService.update(reviewId, requestDto);
        return "redirect:/user/mypage";
    }

    @PostMapping(value = "edit", params = "cmd=delete")
    public String delete(Model model, @RequestParam("reviewId") int reviewId) {
        reviewService.delete(reviewId);
        return "redirect:/user/mypage";
    }

}
