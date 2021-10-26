package com.example.musicdiary2.controller;

import com.example.musicdiary2.dto.DiaryDto;
import com.example.musicdiary2.service.DiaryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
//@RequestMapping("/music-diary")
public class DiaryController {

    private DiaryService diaryService;

    @GetMapping("/")
    public String list(Model model) {
        List<DiaryDto> diaryList = diaryService.getDiaryList();
        model.addAttribute("diaryList", diaryList);
        return "diary/list.html";
    }

    @GetMapping("/post")
    public String write() {
        return "diary/write.html";
    }

    @PostMapping("/post")
    public String write(DiaryDto diaryDto) {
        diaryService.savePost(diaryDto);
        return "redirect:/";
    }

    @GetMapping("/post/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        DiaryDto diaryDto = diaryService.getPost(id);
        model.addAttribute("diaryDto", diaryDto);
        return "diary/detail.html";
    }

    @GetMapping("/post/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        DiaryDto diaryDto = diaryService.getPost(id);
        model.addAttribute("diaryDto", diaryDto);
        return "diary/update.html";
    }

    @PutMapping("/post/edit/{id}")
    public String update(DiaryDto diaryDto) {
        diaryService.savePost(diaryDto);
        return "redirect:/";
    }

    @DeleteMapping("/post/{id}")
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
