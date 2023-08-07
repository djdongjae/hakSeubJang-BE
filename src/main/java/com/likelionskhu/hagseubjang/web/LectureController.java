package com.likelionskhu.hagseubjang.web;

import com.likelionskhu.hagseubjang.config.auth.dto.SessionUser;
import com.likelionskhu.hagseubjang.domain.lecture.Lecture;
import com.likelionskhu.hagseubjang.domain.user.UserRepository;
import com.likelionskhu.hagseubjang.service.LectureService;
import com.likelionskhu.hagseubjang.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("lecture")
public class LectureController {

    private final LectureService lectureService;
    private final WishService wishService;
    private final HttpSession httpSession;
    private final UserRepository userRepository;

    @GetMapping ("list")
    public String list(Model model) {
        model.addAttribute("lectures", lectureService.findAll());
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
        }
        return "lecture/list";
    }

    @GetMapping("detail")
    public String detail(Model model, int id) {
        model.addAttribute("lecture", lectureService.findById(id));
        return "lecture/detail";
    }

    @GetMapping("create/wish")
    public String createWish(Model model, @RequestParam("lectureId") int lectureId) {
        wishService.save(lectureId);
        return "redirect:/lecture/list";
    }

    @GetMapping("delete/wish")
    public String deleteWish(Model model, @RequestParam("wishId") int wishId) {
        wishService.delete(wishId);
        return "redirect:/lecture/list";
    }

    @RequestMapping("load_save")
    public String loadSave(Model model) throws Exception {
        lectureService.loadSave();
        return "download_success";
    }

}
