package com.zte.msg.pushcenter.pccore.utils;

import java.sql.Timestamp;
import java.util.Date;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/22 12:42
 */
public class DateUtils {


    public static String formatDate(Date date) {

        return org.apache.http.client.utils.DateUtils.formatDate(date, Constants.DATE_FORMAT_PATTERN);
    }

    public static Timestamp zeroClockOf(long mill) {

        return new Timestamp(mill - mill % Constants.DAY_MILL);
    }

    public static Timestamp sevenDayBefore(long mill) {
        return new Timestamp(mill - mill % Constants.DAY_MILL + Constants.DAY_MILL * 7);
    }

    public static Timestamp now() {
        return now(System.currentTimeMillis());
    }

    public static Timestamp now(long mill) {
        return new Timestamp(mill);
    }
}
