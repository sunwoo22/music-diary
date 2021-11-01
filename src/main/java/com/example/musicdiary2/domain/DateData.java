package com.example.musicdiary2.domain;

import com.example.musicdiary2.dto.DiaryDto;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DateData {

    int day;
    String fill;
    DiaryDto diaryDto;
    int x;
    int y;

    public DateData(int day, DiaryDto diaryDto, int x, int y) {
        this.day = day;
        this.diaryDto = diaryDto;
        this.fill = moodColor(diaryDto.getMood());
        this.x = x;
        this.y = y;
    }

    public DateData(int day, DiaryDto diaryDto, String fill, int x, int y) {
        this.day = day;
        this.diaryDto = diaryDto;
        this.fill = fill;
        this.x = x;
        this.y = y;
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

}
