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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class CalendarController {

    @Autowired
    private DiaryService diaryService;

    @GetMapping("/test-calendar")
    public String calendar(Principal principal, Model model) { // , DateData dateData) {

//        String username = principal.getName();
        String username = "aaa@example.com";

        LocalDate today = LocalDate.now();
        LocalDate minus100Day = today.minusDays(100);


//        Calendar cal = Calendar.getInstance();
//        DateData calendarData;



        /*
        // 검색 날짜
        if (dateData.getDate().equals("") && dateData.getMonth().equals("")) {
            dateData = new DateData(
                    String.valueOf(cal.get(Calendar.YEAR)),
                    String.valueOf(cal.get(Calendar.MONTH)),
                    String.valueOf(cal.get(Calendar.DATE)),
                    null, null);
        }

         */

//        Map<String, Integer> today_info = dateData.today_info(dateData);
        List<DateData> dateList = new ArrayList<DateData>();

        // 검색 날짜 end
        List<DiaryDto> calDiaryList = diaryService.getPostByDate(username, today, minus100Day);


        // 달력 데이터에 넣기 위한 배열 추가
        DiaryDto[] diaryDtos = new DiaryDto[101];
        for (int i=0; i<100; i++) {
            diaryDtos[i] = new DiaryDto();
        }
        if (!calDiaryList.isEmpty()) {
            for (int i=0; i<calDiaryList.size(); i++) {
                int date = calDiaryList.get(i).getCreatedDate().getDayOfYear() - minus100Day.getDayOfYear();
                diaryDtos[date] = calDiaryList.get(i);
                /*
                int date = Integer.parseInt(String.valueOf(calDiaryList.get(i).getCreatedDate())
                        .substring(String.valueOf(calDiaryList.get(i).getCreatedDate()).length()-2,
                                    String.valueOf(calDiaryList.get(i).getCreatedDate()).length()));
                if (i > 0) {
                    int date_before = Integer.parseInt(String.valueOf(calDiaryList.get(i-1).getCreatedDate())
                            .substring(String.valueOf(calDiaryList.get(i-1).getCreatedDate()).length()-2,
                                        String.valueOf(calDiaryList.get(i-1).getCreatedDate()).length()));
                    if (date_before == date) {
                        j += 1;
                        diaryDtos[date] = calDiaryList.get(i);
                    } else {
                        j = 0;
                        diaryDtos[date] = calDiaryList.get(i);
                    }

                } else {
                    diaryDtos[date] = calDiaryList.get(i);
                }

                 */
            }
        }
        for (int i=0; i<100; i++) {
            int day = minus100Day.plusDays(i).getDayOfWeek().getValue();

            DateData calendarDate = new DateData(day, diaryDtos[i]);
            dateList.add(calendarDate);
        }


        /*
        // 실질적인 달력 데이터 리스트에 데이터 삽입 시작
        // 일단 시작 인덱스까지 아무것도 없는 데이터 삽입
        for (int i=0; i<today_info.get("start"); i++) {
            calendarData = new DateData(null, null, null, null, null);
            dateList.add(calendarData);
        }


        // 날짜 삽입
        for (int i=today_info.get("startDay"); i<=today_info.get("endDay"); i++) {
            DiaryDto diaryDtos1 = new DiaryDto();
            diaryDtos1 = diaryDtos[i];

            if (i == today_info.get("today")) {
                calendarData = new DateData(
                        String.valueOf(dateData.getYear()),
                        String.valueOf(dateData.getMonth()),
                        String.valueOf(i),
                        "today", diaryDtos1);
            } else {
                calendarData = new DateData(
                        String.valueOf(dateData.getYear()),
                        String.valueOf(dateData.getMonth()),
                        String.valueOf(i),
                        "normal_date", diaryDtos1);
            }
            dateList.add(calendarData);
        }

        // 달력 빈 곳 빈 데이터로 삽입
        int index = 7 - dateList.size()%7;

        if (dateList.size()%7 != 0) {
            for (int i=0; i<index; i++) {
                calendarData = new DateData(null, null, null, null, null);
                dateList.add(calendarData);
            }
        }

         */

        // 배열에 담음
//        model.addAttribute("diaryDtos", diaryDtos);
        model.addAttribute("dateList", dateList); // 날짜 데이터 배열
//        model.addAttribute("today_info", today_info);

        return "view/calendar";

    }
}
