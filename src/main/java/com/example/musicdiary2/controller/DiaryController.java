package com.example.musicdiary2.controller;

import com.example.musicdiary2.dto.DiaryDto;
import com.example.musicdiary2.service.DiaryService;
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
    public String detail(@PathVariable("id") Long id, Model model) {
        DiaryDto diaryDto = diaryService.getPost(id);
        model.addAttribute("diaryDto", diaryDto);
        return "diary/detail.html";
    }

    @GetMapping("/diary/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        DiaryDto diaryDto = diaryService.getPost(id);
        model.addAttribute("diaryDto", diaryDto);
        return "diary/update.html";
    }

    @PutMapping("/diary/edit/{id}")
    public String update(DiaryDto diaryDto) throws IOException {
        diaryService.savePost(diaryDto);
        return "redirect:/";
    }

    @DeleteMapping("/diary/{id}")
    public String delete(@PathVariable("id") Long id) {
        diaryService.deletePost(id);
        return "redirect:/";
    }

    @GetMapping("/diary/search")
    public String search(@RequestParam(value="keyword") String keyword, Model model) {
        List<DiaryDto> diaryDtoList = diaryService.searchPosts(keyword);
        model.addAttribute("diaryList", diaryDtoList);
        return "diary/list.html";
    }

}
