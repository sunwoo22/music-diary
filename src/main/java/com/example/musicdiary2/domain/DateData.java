package com.example.musicdiary2.domain;

import com.example.musicdiary2.dto.DiaryDto;
import lombok.Data;
import lombok.ToString;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Data
@ToString
public class DateData {

    int day;
    String fill;

//    String year = "";
//    String month = "";
//    String date = "";
//    String value = "";
//
//    String db_startDate = "";
//    String db_endDate = "";

    DiaryDto diaryDto;

    public DateData(int day, DiaryDto diaryDto) {
        this.day = day;
//        this.fill = fill;
        this.diaryDto = diaryDto;
        this.fill = moodColor(diaryDto.getMood());
    }

    public String moodColor(int mood) {
        if (mood == 1) {
            return "#FAED7D";
        } else if (mood == 2) {
            return "#CEF279";
        } else if (mood == 3) {
            return "#B2CCFF";
        } else if (mood == 4) {
            return "#FFC19E";
        } else {
            return "#EAEAEA";
        }
    }


    // 생성자
//    public DateData(String year, String month, String date, String value, DiaryDto diaryDto) {
//        this.year = year;
//        this.month = month;
//        this.date = date;
//        this.value = value;
//        this.diaryDto = diaryDto;
//    }

    /*

    // 날짜에 관련된 달력정보를 가지는 메소드
    public Map<String, Integer> today_info(DateData dateData) {
        // 날짜 캘린더 함수에 삽입
        Map<String, Integer> today_Data = new HashMap<String, Integer>();
        Calendar cal = Calendar.getInstance();
        cal.set(Integer.parseInt(dateData.getYear()), Integer.parseInt(dateData.getMonth()), 1);

        int startDay = cal.getMinimum(Calendar.DATE);
        int endDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int start = cal.get(Calendar.DAY_OF_WEEK);

        Calendar todayCal = Calendar.getInstance();
        SimpleDateFormat ysdf = new SimpleDateFormat("yyyy");
        SimpleDateFormat msdf = new SimpleDateFormat("MM");

        int today_year = Integer.parseInt(ysdf.format(todayCal.getTime()));
        int today_month = Integer.parseInt(ysdf.format(todayCal.getTime()));

        int search_year = Integer.parseInt(dateData.getYear());
        int search_month = Integer.parseInt(dateData.getMonth()) + 1;

        int today = -1;
        if (today_year == search_year && today_month == search_month) {
            SimpleDateFormat dsdf = new SimpleDateFormat("dd");
            today = Integer.parseInt(dsdf.format(todayCal.getTime()));
        }

        // 날짜 관련
        System.out.println("search_month : " + search_month);
        // 캘린더 함수 end
        today_Data.put("start", start);
        today_Data.put("startDay", startDay);
        today_Data.put("endDay", endDay);
        today_Data.put("today", today);
        today_Data.put("search_year", search_year);
        today_Data.put("search_month", search_month+1);

        this.db_startDate = String.valueOf(search_year) + "." + String.valueOf(search_month+1) + "-" + String.valueOf(startDay);
        this.db_endDate = String.valueOf(search_year) + "-" + String.valueOf(search_month+1) + "-" + String.valueOf(endDay);

        return today_Data;
    }

     */


}
