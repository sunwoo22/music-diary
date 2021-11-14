package com.example.musicdiary2.controller;

import com.example.musicdiary2.dto.DiaryDto;
import com.example.musicdiary2.service.DiaryService;
import com.example.musicdiary2.service.LikeService;
import com.example.musicdiary2.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class DiaryController {

    private DiaryService diaryService;
    private UserService userService;
    private LikeService likeService;

    @GetMapping("/diary/music")
    public String music() {
        return "diary/musicSearch.html";
    }

    @PostMapping("/diary/write")
    public String music(@RequestParam String title, @RequestParam String singer,
                        Principal principal, Model model, HttpServletResponse response) throws IOException {
        String[] result = diaryService.setMusic(title, singer);
        if (result == null) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>" +
                            "alert('검색 결과가 없습니다.');" +
                            "location.href='/diary/music';" +
                        "</script>");
            out.flush();
//            return "redirect:/diary/music";
            return null;
        }

        model.addAttribute("title", result[0]);
        model.addAttribute("singer", result[1]);
        model.addAttribute("imgSrc", result[2]);

        String username = principal.getName();
        model.addAttribute("username", username);

        return "diary/write";
    }

    @GetMapping("/diary/write")
    public String write(Principal principal, Model model) {
        String username = principal.getName();
        model.addAttribute("username", username);
        return "diary/write.html";
    }

    @PostMapping("/diary/save")
    public String write(DiaryDto diaryDto) throws IOException {
        diaryService.savePost(diaryDto);
        return "redirect:/mypage";
    }

    @GetMapping("/diary/{id}")
    public String detail(@PathVariable("id") Long id,
                         Principal principal, Model model) {
        diaryService.increaseViews(id);
        DiaryDto diaryDto = diaryService.getPost(id);
        model.addAttribute("diaryDto", diaryDto);

        String username = null;
        if (principal != null) {
            username = principal.getName();
            Long user_id = userService.getUserId(username);
            boolean checkLike = likeService.checkLike(id, user_id);
            model.addAttribute("checkLike", checkLike);
        }
        model.addAttribute("username", username);

        int likeNum = likeService.countLikeNum(id);
        model.addAttribute("likeNum", likeNum);

        return "diary/detail.html";
    }

    @PostMapping("/diary/like")
    public String pushLike(@RequestParam("diary_id") Long diary_id,
                           @RequestParam("username") String username,
                           @RequestParam("checkLike") boolean checkLike) {
        Long user_id = userService.getUserId(username);

        if (checkLike) {
            likeService.cancelLike(diary_id, user_id);
            diaryService.decreaseLikes(diary_id);
        } else {
            likeService.pushLike(diary_id, user_id);
            diaryService.increaseLikes(diary_id);
        }

        return "redirect:/diary/" + diary_id;
    }

    @PutMapping("/diary/edit/{id}")
    public String updateUnopen(@PathVariable("id") Long id,
                               @RequestParam("unopen") int unopen) {
        diaryService.updateUnopen(id, unopen);
        return "redirect:/mypage";
    }

    @DeleteMapping("/diary/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        diaryService.deletePost(id);
        return "redirect:/mypage";
    }

    @GetMapping("/diary/search")
    public String search(@RequestParam(value="keyword") String keyword, Model model) {
        List<DiaryDto> diaryDtoList = diaryService.searchPosts(keyword);
        model.addAttribute("diaryList", diaryDtoList);
        return "diary/list.html";
    }

}
