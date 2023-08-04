package com.likelionskhu.hagseubjang.web;

import com.likelionskhu.hagseubjang.service.ReviewService;
import com.likelionskhu.hagseubjang.web.dto.ReviewSaveRequestDto;
import com.likelionskhu.hagseubjang.web.dto.ReviewUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("")
    public String create(@RequestBody ReviewSaveRequestDto reviewSaveRequestDto) {
        reviewService.save(reviewSaveRequestDto);
        return "redirect:list";
    }

    @PutMapping("")
    public String update(@PathVariable int id, @RequestBody ReviewUpdateRequestDto requestDto) {
        reviewService.update(id, requestDto);
        return "redirect:list";
    }

    @DeleteMapping("")
    public String delete(@PathVariable int id) {
        reviewService.delete(id);
        return "redirect:list";
    }

}
