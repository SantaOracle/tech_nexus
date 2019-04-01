package org.tech.accumulation.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author peiheng.jiang create on 2019/03/28
 */
public class DateUtil {

    /**
     * 按照指定格式生成Date
     * @param pattern   eg. yyyy-MM-dd
     * @param date      eg. 2019-03-25
     * @return
     * @throws ParseException
     */
    public static Date parse(String pattern, String date) throws ParseException {
        return new SimpleDateFormat(pattern).parse(date);
    }

    /**
     * 按照指定类型格式化Date
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }
}
