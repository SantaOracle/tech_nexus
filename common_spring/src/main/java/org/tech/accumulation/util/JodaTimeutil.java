package org.tech.accumulation.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Create by peiheng.jiang on 2019/9/27
 */
@Slf4j
public class JodaTimeutil {

    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");

    /**
     * 时间差，返回差值分钟数
     * @param d1
     * @param d2
     * @return
     */
    public static int getTimeInterval(DateTime d1, DateTime d2) {
        return Minutes.minutesBetween(d1, d2).getMinutes();
    }

    public static int getTimeInterval(String date1, String time1, String date2, String time2) {
        DateTime d1 = null;
        if(StringUtils.isNotEmpty(date1) && StringUtils.isNotEmpty(time1)) {
            d1 = parseDate(new StringBuilder(date1).append(" ").append(time1).toString(), dateTimeFormatter);
        }
        DateTime d2 = null;
        if(StringUtils.isNotEmpty(date2) && StringUtils.isNotEmpty(time2)) {
            d2 = parseDate(new StringBuilder(date2).append(" ").append(time2).toString(), dateTimeFormatter);
        }

        if(d1 == null || d2 == null){
            return 0;
        }

        return getTimeInterval(d1, d2);
    }

    public static DateTime parseDate(String dateStr, DateTimeFormatter formatter) {
        return parseDate(dateStr, formatter, true);
    }

    public static DateTime parseDate(String dateStr, DateTimeFormatter formatter, boolean isLog) {
        DateTime dateTime = null;
        try {
            dateTime = formatter.parseDateTime(dateStr);
        } catch (Exception e) {
            if(isLog) log.error("Error in parse date: {} ! ", dateStr, e);
        }
        return dateTime;
    }

    public static void main(String[] args) {
        DateTime dt1 = new DateTime(2019, 9, 27, 15, 0);
        DateTime dt2 = new DateTime(2019, 9, 27, 13, 0);
        System.out.println(getTimeInterval(dt1, dt2));
    }
}
