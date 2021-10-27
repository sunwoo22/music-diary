package com.example.musicdiary2.controller;

import com.example.musicdiary2.dto.DiaryDto;
import com.example.musicdiary2.dto.UserDto;
import com.example.musicdiary2.service.DiaryService;
import com.example.musicdiary2.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
//@RequestMapping(value = "/music-diary")
public class DiaryController {

    private DiaryService diaryService;

//    @GetMapping("/")
//    public String list(Model model) {
//        List<DiaryDto> diaryList = diaryService.getDiaryList();
//        model.addAttribute("diaryList", diaryList);
//        return "diary/list.html";
//    }


    @GetMapping("/diary/write")
    public String write(Principal principal, Model model) {
        String username = principal.getName();
        model.addAttribute("username", username);
        return "diary/write.html";
    }

    @PostMapping("/diary/write")
    public String write(DiaryDto diaryDto) {
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
    public String update(DiaryDto diaryDto) {
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
