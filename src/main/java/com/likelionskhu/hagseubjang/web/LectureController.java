package com.likelionskhu.hagseubjang.web;

import com.likelionskhu.hagseubjang.service.LectureService;
import com.likelionskhu.hagseubjang.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("lecture")
public class LectureController {

    private final LectureService lectureService;
    private final WishService wishService;

    @GetMapping ("list")
    public String list(Model model) {
        model.addAttribute("lectures", lectureService.findAll());
        return "lecture/list";
    }

    @GetMapping("detail")
    public String detail(Model model, int id) {
        model.addAttribute("lecture", lectureService.findById(id));
        return "lecture/detail";
    }

    @PostMapping("create/wish")
    public String createWish(Model model, @RequestParam("lectureId") int lectureId) {
        wishService.save(lectureId);
        return "redirect:list";
    }

    @PostMapping("delete/wish")
    public String deleteWish(Model model, @RequestParam("wishId") int wishId) {
        wishService.delete(wishId);
        return "redirect:list";
    }

    @RequestMapping("load_save")
    public String loadSave(Model model) throws Exception {
        lectureService.loadSave();
        return "download_success";
    }

}
