package com.example.musicdiary2.controller;

import com.example.musicdiary2.dto.DiaryDto;
import com.example.musicdiary2.dto.UserDto;
import com.example.musicdiary2.service.DiaryService;
import com.example.musicdiary2.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
//@RequestMapping(value = "/login2")
public class UserController {

    private UserService userService;
    private DiaryService diaryService;

    // 메인 페이지
    @GetMapping("")
    public String index(Model model) {
        List<DiaryDto> diaryList = diaryService.getDiaryList();
        model.addAttribute("diaryList", diaryList);

        return "signuplogin/index";
    }

    // 회원가입 페이지
    @GetMapping("/user/signup")
    public String dispSignup() {
        return "signuplogin/signup";
    }

    // 회원가입 처리
    @PostMapping("/user/signup")
    public String execSignup(UserDto userDto) {
        userService.joinUser(userDto);
        return "redirect:/user/login";
    }

    // 로그인 페이지
    @GetMapping("/user/login")
    public String dispLogin() {
        return "signuplogin/login";
    }

    // 로그인 결과 페이지
    @GetMapping("/user/login/result")
    public String dispLoginResult() {
        return "signuplogin/loginSuccess";
    }

    // 로그아웃 결과 페이지
    @GetMapping("/user/logout/result")
    public String dispLogout() {
        return "signuplogin/logout";
    }

    // 접근 거부 페이지
    @GetMapping("/user/denied")
    public String dispDenied() {
        return "signuplogin/denied";
    }

    // 내 정보 페이지
    @GetMapping("/user/info")
    public String dispMyInfo() {
        return "signuplogin/myinfo";
    }

    // 어드민 페이지
    @GetMapping("/admin")
    public String dispAdmin() {
        return "signuplogin/admin";
    }

}
