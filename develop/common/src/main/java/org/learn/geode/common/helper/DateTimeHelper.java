package org.learn.geode.common.helper;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by mithut on 3/26/2017.
 */
public final class DateTimeHelper {

    public static final String DATE_PATTERN = "yyyy/MM/dd";

    public static final String DATE_TIME_PATTERN = "yyyyMMddhhmmss";

    public static final String DATE_TIME_PATTERN_UI = "yyyyMMdd hh:mm:ss";

    private static final SimpleDateFormat formatter = new SimpleDateFormat(DATE_PATTERN);
    private static final DateTimeFormatter localDateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public  DateTimeHelper(){}

    public static String formatDate(Date date) {
        formatter.setLenient(true);
        return formatter.format(date);
    }

    public static String formatLocalDate(LocalDate date) {
        return date.format(localDateTimeFormatter);
    }

    public static String formatDate(Date date, String pattern) throws ParseException {
        final SimpleDateFormat patternFormatter = new SimpleDateFormat(pattern);
        patternFormatter.setLenient(true);
        return patternFormatter.format(date);
    }

    public static Date parseDate(String dateStr) throws ParseException {
        formatter.setLenient(true);
        return formatter.parse(dateStr);
    }

    public static Timestamp parseToSqlDate(Date date) {
        formatter.setLenient(true);
        Timestamp ft = null;
        try {
            Date parsedDate = formatter.parse(formatDate(date));
            ft = new Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ft;
    }

    public static Date asDate(LocalDate localDate) {
        if(localDate == null)
            return null;
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }
}
