package com.example.musicdiary2.calendar;

import com.example.musicdiary2.dto.CalendarDto;
import com.example.musicdiary2.dto.DiaryDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Calendar {

    public static List<CalendarDto> getDateList(List<DiaryDto> calDiaryList) {

        LocalDate today = LocalDate.now();
        LocalDate minus100Day = today.minusDays(100);

        List<CalendarDto> dateList = new ArrayList<>();

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
        int x = 20;
        for (int i=0; i<101; i++) {
            int day = minus100Day.plusDays(i).getDayOfWeek().getValue();
            int y = locY(day);
            if (day%7 == 0) {
                x += 20;
            }
            CalendarDto calendarDate;
            if (x == 20) {
                calendarDate = new CalendarDto(day, diaryDtos[i], "#FFFFFF", x, y);
            } else {
                calendarDate = new CalendarDto(day, diaryDtos[i], x, y);
            }
            dateList.add(calendarDate);
        }

        return dateList;
    }

    public static int locY(int day) {
        if (day == 7) {
            return 0;
        } else if (day == 1) {
            return 20;
        } else if (day == 2) {
            return 40;
        } else if (day == 3) {
            return 60;
        } else if (day == 4) {
            return 80;
        } else if (day == 5) {
            return 100;
        } else if (day == 6) {
            return 120;
        } else {
            return 0;
        }
    }

}
