package com.example.musicdiary2.controller;

import com.example.musicdiary2.dto.DiaryDto;
import com.example.musicdiary2.dto.UserDto;
import com.example.musicdiary2.service.DiaryService;
import com.example.musicdiary2.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import static com.example.musicdiary2.calendar.Calendar.getDateList;

@Controller
@AllArgsConstructor
public class UserController {

    @Autowired
    ServletContext servletContext;

    BCryptPasswordEncoder pwdEncoder;

    private UserService userService;
    private DiaryService diaryService;

    // 회원가입 get
    @GetMapping("/user/register")
    public String getRegister() throws Exception {
        return "signuplogin/register";
    }

    // 회원가입 post
    @PostMapping("/user/register")
    public String postResgister(UserDto userDto, Model model) throws Exception {
        // 이메일 중복여부 확인 (사용가능하면 0)
        int result = userService.idChk(userDto.getEmail());

        try {
            // 이메일 중복일 때
            if (result == 1) {
                model.addAttribute("result", "fail");
                return "redirect:/user/register";
            // 이메일 중복 아닐 때
            } else {
                // db에 회원가입 정보 저장, authkey 생성, 이메일 발송
                userService.register(userDto);
                model.addAttribute("result", "ok");
                return "redirect:/user/login";
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    // 이메일 인증 확인 후
    @GetMapping("/emailConfirm")
    public String emailConfirm(String email, Model model) throws Exception {
        // authstatus 권한 상태 1로 변겅
        userService.updateAuthstatus(email);

        model.addAttribute("email", email);
        return "signuplogin/emailConfirm";
    }

    // 이메일 중복 체크
    @ResponseBody
    @PostMapping("/user/idChk")
    public int idChk(UserDto userDto) throws Exception {
        int result = userService.idChk(userDto.getEmail());
        return result;
    }

    // 메인 페이지
    @GetMapping("/main")
    public String dispMain(Model model) {
        List<DiaryDto> diaryList = diaryService.getDiaryList();
        model.addAttribute("diaryList", diaryList);

        return "view/main";
    }

    // 내 정보 페이지
    @GetMapping("/mypage")
    public String dispMypage(Principal principal, Model model) {
        String username = principal.getName();
        List<DiaryDto> myDiaryList = diaryService.getMyDiaryList(username);
        model.addAttribute("myDiaryList", myDiaryList);

        LocalDate today = LocalDate.now();
        LocalDate minus100Day = today.minusDays(100);
        List<DiaryDto> calDiaryList = diaryService.getPostByDate(username, today, minus100Day);
        model.addAttribute("dateList", getDateList(calDiaryList));

        return "view/mypage";
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

    // 어드민 페이지
    @GetMapping("/admin")
    public String dispAdmin() {
        return "signuplogin/admin";
    }

}
