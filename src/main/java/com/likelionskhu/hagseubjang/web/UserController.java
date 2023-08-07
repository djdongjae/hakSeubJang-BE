package com.likelionskhu.hagseubjang.web;

import com.likelionskhu.hagseubjang.service.ReviewService;
import com.likelionskhu.hagseubjang.service.UserService;
import com.likelionskhu.hagseubjang.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
@RequestMapping("user")
public class UserController {

    private final UserService userService;
    private final ReviewService reviewService;
    private final WishService wishService;

    @GetMapping("main")
    public String user(Model model) {
        model.addAttribute("reviews", userService.findReviews());
        model.addAttribute("wishes", userService.findWishes());
        return "user/main";
    }

    @GetMapping("login")
    public String login(Model model) {
        return "user/login";
    }

    @RequestMapping("delete/wish")
    public String deleteWish(Model model, @RequestParam("wishId") int wishId) {
        wishService.delete(wishId);
        return "user/main";
    }

    @RequestMapping("delete/review")
    public String deleteReview(Model model, @RequestParam("reviewId") int reviewId) {
        reviewService.delete(reviewId);
        return "redirect:/user/main";
    }

}
