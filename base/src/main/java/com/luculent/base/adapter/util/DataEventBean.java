package com.luculent.base.adapter.util;

import androidx.annotation.NonNull;

import java.io.Serializable;

/**
 * @Author byz
 * @CreateData 2020/11/30 19:44
 */
public class DataEventBean implements Serializable {
    private int year;
    private int month;
    private int day;

    public DataEventBean(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @NonNull
    @Override
    public String toString() {
        return "year:"+getYear()+"\nmonth:"+getMonth()+"\ndat:"+getDay();
    }
}
