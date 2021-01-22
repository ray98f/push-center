package com.zte.msg.pushcenter.pccore.utils;

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
}
