package com.likelionskhu.hagseubjang.web;

import com.likelionskhu.hagseubjang.config.auth.dto.SessionUser;
import com.likelionskhu.hagseubjang.domain.lecture.Lecture;
import com.likelionskhu.hagseubjang.domain.wish.Wish;
import com.likelionskhu.hagseubjang.service.LectureService;
import com.likelionskhu.hagseubjang.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

    @GetMapping("list")
    public String list(
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size,
            @RequestParam(value = "region", required = false) String region,
            @RequestParam(value = "edcMthType", required = false) String edcMthType,
            @RequestParam(value = "edcTrgetType", required = false) String edcTrgetType,
            HttpSession session
    ) {

        if (region == null) region = "";
        if (edcMthType == null) edcMthType = "";
        if (edcTrgetType == null) edcTrgetType = "";

        session.setAttribute("region", region);
        session.setAttribute("edcMthType", edcMthType);
        session.setAttribute("edcTrgetType", edcTrgetType);

//        String regionOnView = (String) session.getAttribute("region");
//        model.addAttribute("regionOnView", regionOnView);

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(9);

        Page<Lecture> lecturePage = lectureService.findPaginatedInFilter(
                PageRequest.of(currentPage - 1, pageSize),
                region, edcMthType, edcTrgetType
        );

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageSize", pageSize);


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
            for (Lecture lecture : lecturePage) {
                lecture.setInWish(false);
                for (Wish wish : lecture.getWishes()) {
                    if (wish.getUser().getEmail().equals(user.getEmail())) {
                        lecture.setInWish(true);
                    }
                }
            }
        }

        model.addAttribute("lecturePage", lecturePage);

        return "lecture/list";
    }

    @GetMapping("list2")
    public String list2(
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size,
            @RequestParam("srchText") String srchText,
            HttpSession session
    ) {

        if (srchText == null) srchText = "";

        session.setAttribute("srchText", srchText);

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(9);

        Page<Lecture> lecturePage = lectureService.findPaginatedInSearch(
                PageRequest.of(currentPage - 1, pageSize),
                srchText
        );

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageSize", pageSize);

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
            for (Lecture lecture : lecturePage) {
                lecture.setInWish(false);
                for (Wish wish : lecture.getWishes()) {
                    if (wish.getUser().getEmail().equals(user.getEmail())) {
                        lecture.setInWish(true);
                    }
                }
            }
        }
        model.addAttribute("lecturePage", lecturePage);
        return "lecture/list2";
    }

    @GetMapping("detail")
    public String detail(Model model, int id) {
        Lecture lecture = lectureService.findById(id);
        String homepageUrl = lecture.getHomepageUrl();
        lecture.setHomepageUrl(removefirstChar(homepageUrl, "https://", "http://"));
        model.addAttribute("lecture", lecture);
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
            lecture.setInWish(false);
            for (Wish wish : lecture.getWishes()) {
                if (wish.getUser().getEmail().equals(user.getEmail())) {
                    lecture.setInWish(true);
                }
            }
        }
        return "lecture/detail";
    }

    public static String removefirstChar(String str, String start, String start2)
    {
        if (str == null || str.length() == 0) {
            return str;
        }
        if (!(str.startsWith(start) || str.startsWith(start2))) {
            return "https://" + str;
        }
        return str;
    }

    @GetMapping("wish")
    public String wish(
            Model model,
            @RequestParam("lectureId") int lectureId,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size,
            HttpSession session
    ) {
        wishService.saveOrDelete(lectureId);

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        String region = (String) session.getAttribute("region");
        String edcMthType = (String) session.getAttribute("edcMthType");
        String edcTrgetType = (String) session.getAttribute("edcTrgetType");

        String result = new StringBuilder()
                .append("redirect:/lecture/list?")
                .append(String.format("page=%d&size=%d", currentPage, pageSize))
                .append("&region=" + toURLEncodeUtf8(region))
                .append("&edcMthType=" + toURLEncodeUtf8(edcMthType))
                .append("&edcTrgetType=" + toURLEncodeUtf8(edcTrgetType))
                .toString();

        return result;
    }


    @GetMapping("wish2")
    public String wish2(
            Model model,
            @RequestParam("lectureId") int lectureId,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size,
            HttpSession session
    ) {
        wishService.saveOrDelete(lectureId);

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        String srchText = (String) session.getAttribute("srchText");

        String result = new StringBuilder()
                .append("redirect:/lecture/list2?")
                .append(String.format("page=%d&size=%d", currentPage, pageSize))
                .append("&srchText=" + toURLEncodeUtf8(srchText))
                .toString();

        return result;
    }

    @GetMapping("wish3")
    public String wish3(
            Model model,
            @RequestParam("lectureId") int lectureId
    ) {
        wishService.saveOrDelete(lectureId);

        return "redirect:/lecture/detail?id=" + lectureId;
    }

    @RequestMapping("load_save")
    public String loadSave(Model model) throws Exception {
        lectureService.loadSave();
        return "download_success";
    }

    public static String toURLEncodeUtf8(String str) {
        if (str == null || str.trim().equals("")) return "";
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            return null;
        }
    }

}
