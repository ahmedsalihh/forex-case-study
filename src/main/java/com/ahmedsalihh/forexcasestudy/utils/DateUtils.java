package com.ahmedsalihh.forexcasestudy.utils;

import com.ahmedsalihh.forexcasestudy.exception.DateNotRecognizedException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static LocalDate formatDate(String dateString) {
        LocalDate date;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(dateString, formatter);
        } catch (Exception e) {
            throw new DateNotRecognizedException("Wrong date format. Date should be provided in 'yyyy-MM-dd' format");
        }

        return date;
    }
}
