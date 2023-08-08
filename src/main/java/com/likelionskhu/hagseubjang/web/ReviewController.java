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
    public String create(Model model, @RequestParam("lectureId") int lectureId) {
        model.addAttribute("review", reviewService.create(lectureId));
        return "review/edit";
    }

    @PostMapping("create")
    public String create(Model model, Review review) {
        reviewService.save(review);
        return "redirect:/lecture/detail?id=" + review.getLecture().getId();
    }

    @GetMapping("edit")
    public String edit(Model model, @RequestParam("reviewId") int reviewId) {
        model.addAttribute("review", reviewService.findById(reviewId));
        return "review/edit";
    }

    @PostMapping (value = "edit", params = "cmd=save")
    public String edit(Model model, @RequestParam("reviewId") int reviewId, ReviewUpdateRequestDto requestDto) {
        reviewService.update(reviewId, requestDto);
        return "redirect:/user";
    }

    @PostMapping(value = "edit", params = "cmd=delete")
    public String delete(Model model, @RequestParam("reviewId") int reviewId) {
        reviewService.delete(reviewId);
        return "redirect:/user";
    }

}
