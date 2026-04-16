package com.example.sit305_4_1p;

import androidx.room.TypeConverter;

import java.time.LocalDate;
import java.time.LocalTime;

public class Converters {
    @TypeConverter
    public static String fromLocalDate(LocalDate date) {
        if (date == null) {
            return null;
        }
        else {
            return date.toString();
        }
    }
    @TypeConverter
    public static LocalDate toLocalDate(String strDate) {
        if (strDate != null) {
            return LocalDate.parse(strDate);
        }
        else {
            return null;
        }
    }

    @TypeConverter
    public static String fromLocalTime(LocalTime time) {
        if (time == null) {
            return null;
        }
        else {
            return time.toString();
        }
    }

    @TypeConverter
    public static LocalTime toLocalTime(String strTime) {
        if (strTime != null) {
            return LocalTime.parse(strTime);
        }
        else {
            return null;
        }
    }
}
