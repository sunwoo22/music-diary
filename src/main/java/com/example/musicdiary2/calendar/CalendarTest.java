package com.example.musicdiary2.calendar;

import java.time.LocalDate;
import java.util.Calendar;

public class CalendarTest {
    public static void main(String[] args) {
//        Calendar today = Calendar.getInstance();
//        String year = Integer.toString(today.get(Calendar.YEAR));
//        int month = today.get(Calendar.MONTH);
//        int date = today.get(Calendar.DATE);

        LocalDate today = LocalDate.now();
//        int year = today.getYear();
//        int month = today.getMonthValue();
//        int date = today.getDayOfMonth();
//        LocalDate minus100Day = today.minusDays(100);
//        int myear = minus100Day.getYear();
//        int mmonth = minus100Day.getMonthValue();
//        int mdate = minus100Day.getDayOfMonth();
//
//        System.out.println("year" + year);
//        System.out.println("month" + month);
//        System.out.println("date" + date);
//
//        System.out.println("myear" + myear);
//        System.out.println("mmonth" + mmonth);
//        System.out.println("mate" + mdate);

        System.out.println(today.getDayOfWeek());


    }
}
