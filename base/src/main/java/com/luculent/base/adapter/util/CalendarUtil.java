package com.luculent.base.adapter.util;


import java.util.Calendar;
import java.util.TimeZone;

/**
 * @Author byz
 * @CreateData 2020/11/30 18:48
 * @blame Android Team
 */

public class CalendarUtil {
    static Calendar cal;
    public static int year;
    public static int month;
    public static int day;
    public static int now_year = 0;
    public static int now_month = 0;
    public static int now_day = 0;

    public CalendarUtil() {
    }

    public static int ZellerWeek(int year, int month, int day) {
        int m = month;
        if (month <= 2) {
            --year;
            m = month + 12;
        }

        int y = year % 100;
        int c = year / 100;
        int w = (y + y / 4 + c / 4 - 2 * c + 13 * (m + 1) / 5 + day - 1) % 7;
        if (w < 0) {
            w += 7;
        }

        return w;
    }

    public static int getYearsMouth2Day(int year, int month) {
        int sum = 0;
        if ((year % 4 != 0 || year % 100 == 0) && year % 400 != 0) {
            sum = 28;
        } else {
            sum = 29;
        }

        switch(month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 2:
                return sum;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                return 0;
        }
    }

    public static DataEventBean getNowDate() {
        cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        year = cal.get(1);
        month = cal.get(2) + 1;
        day = cal.get(5);
        DataEventBean dataEventBean = new DataEventBean(year,month,day);
       return dataEventBean;
    }


    public static int getWeekNum(){
        cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        year = cal.get(1);
        month = cal.get(2) + 1;
        day = cal.get(5);
        return ZellerWeek(year,month,day);
    }


}
