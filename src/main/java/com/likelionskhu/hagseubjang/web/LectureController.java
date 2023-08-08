package com.likelionskhu.hagseubjang.web;

import com.likelionskhu.hagseubjang.config.auth.dto.SessionUser;
import com.likelionskhu.hagseubjang.domain.lecture.Lecture;
import com.likelionskhu.hagseubjang.service.LectureService;
import com.likelionskhu.hagseubjang.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Controller
@RequestMapping("lecture")
public class LectureController {

    private final LectureService lectureService;
    private final WishService wishService;
    private final HttpSession httpSession;

    @GetMapping ("list")
    public String list(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        Page<Lecture> lecturePage = lectureService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("lecturePage", lecturePage);

        int totalPages = lecturePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

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

    @GetMapping("wish")
    public String wish(Model model, @RequestParam("lectureId") int lectureId, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        wishService.saveOrDelete(lectureId);

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        return "redirect:/lecture/list?" + String.format("page=%d&size=%d", currentPage, pageSize);
    }

    @RequestMapping("load_save")
    public String loadSave(Model model) throws Exception {
        lectureService.loadSave();
        return "download_success";
    }

}
