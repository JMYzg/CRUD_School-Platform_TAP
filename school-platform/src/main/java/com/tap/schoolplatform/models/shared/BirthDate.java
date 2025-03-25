package com.tap.schoolplatform.models.shared;

import java.time.LocalDate;
import java.time.Period;

public class BirthDate {
    int day;
    int month;
    int year;

    public BirthDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }
    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }
    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    public int calculateAge() {
        LocalDate now = LocalDate.now();
        LocalDate birthDate = LocalDate.of(year, month, day);
        return Period.between(birthDate, now).getYears();
    }

    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
    }
}
