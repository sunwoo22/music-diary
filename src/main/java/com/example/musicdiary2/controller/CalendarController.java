package com.example.musicdiary2.controller;

import com.example.musicdiary2.domain.DateData;
import com.example.musicdiary2.dto.DiaryDto;
import com.example.musicdiary2.service.DiaryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class CalendarController {

    @Autowired
    private DiaryService diaryService;

//    @GetMapping("/test-calendar")
    public String calendar(Principal principal, Model model) {

        String username = principal.getName();
//        String username = "aaa@example.com";

        LocalDate today = LocalDate.now();
        LocalDate minus100Day = today.minusDays(100);

        List<DateData> dateList = new ArrayList<>();

        // 검색 날짜 end
        List<DiaryDto> calDiaryList = diaryService.getPostByDate(username, today, minus100Day);

        // 달력 데이터에 넣기 위한 배열 추가
        DiaryDto[] diaryDtos = new DiaryDto[101];
        for (int i=0; i<101; i++) {
            diaryDtos[i] = new DiaryDto();
        }
        if (!calDiaryList.isEmpty()) {
            for (int i=0; i<calDiaryList.size(); i++) {
                int date = calDiaryList.get(i).getCreatedDate().getDayOfYear() - minus100Day.getDayOfYear();
                diaryDtos[date] = calDiaryList.get(i);
            }
        }

        // 실질적인 달력 데이터 리스트에 데이터 삽입 시작
        int x = 40;
        for (int i=0; i<101; i++) {
            int day = minus100Day.plusDays(i).getDayOfWeek().getValue();
            int y = locY(day);
            if (day%7 == 0) {
                x += 20;
            }
            DateData calendarDate;
            if (x == 40) {
                calendarDate = new DateData(day, diaryDtos[i], "#FFFFFF", x, y);
            } else {
                calendarDate = new DateData(day, diaryDtos[i], x, y);
            }
            dateList.add(calendarDate);
        }

        // 배열에 담음
        model.addAttribute("dateList", dateList); // 날짜 데이터 배열

        return "view/calendar";
    }

    public int locY(int day) {
        if (day == 7) {
            return 40;
        } else if (day == 1) {
            return 60;
        } else if (day == 2) {
            return 80;
        } else if (day == 3) {
            return 100;
        } else if (day == 4) {
            return 120;
        } else if (day == 5) {
            return 140;
        } else if (day == 6) {
            return 160;
        } else {
            return 0;
        }
    }
}
