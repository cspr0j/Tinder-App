package org.tinder.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Converter {
    private static final SimpleDateFormat formatForDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String converterToString(Timestamp date) {
        return formatForDate.format(date);
    }
}