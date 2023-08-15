package com.likelionskhu.hagseubjang.web;

import com.likelionskhu.hagseubjang.config.auth.dto.SessionUser;
import com.likelionskhu.hagseubjang.domain.wish.Wish;
import com.likelionskhu.hagseubjang.service.ReviewService;
import com.likelionskhu.hagseubjang.service.UserService;
import com.likelionskhu.hagseubjang.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@RequiredArgsConstructor
@Controller
@RequestMapping("user")
public class UserController {

    private final UserService userService;
    private final ReviewService reviewService;
    private final WishService wishService;
    private final HttpSession httpSession;

    @GetMapping("mypage")
    public String user(Model model) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("reviews", userService.findReviews());
        for (Wish wish : userService.findWishes()) {
            wish.getLecture().setRemainDay(LocalDate.now().until(wish.getLecture().getRceptEndDate(), ChronoUnit.DAYS));
        }
        model.addAttribute("wishes", userService.findWishes());
        return "user/mypage";
    }

    @GetMapping("login")
    public String login(Model model) {
        return "user/login";
    }

    @RequestMapping("delete/wish")
    public String deleteWish(Model model, @RequestParam("wishId") int wishId) {
        wishService.delete(wishId);
        return "redirect:/user/mypage";
    }

    @RequestMapping("delete/review")
    public String deleteReview(Model model, @RequestParam("reviewId") int reviewId) {
        reviewService.delete(reviewId);
        return "redirect:/user/mypage";
    }

}
