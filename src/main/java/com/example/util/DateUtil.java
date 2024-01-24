package com.example.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private DateUtil(){
    }

    public static String dateToString(Date date) {
        return SIMPLE_DATE_FORMAT.format(date);
    }

    public static Date stringToDate(String dateStr) {
        Date date = null;
        try {
            if (!dateStr.isEmpty()) {
            date = SIMPLE_DATE_FORMAT.parse(dateStr);}
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }
}
