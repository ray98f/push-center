package com.zte.msg.pushcenter.utils;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * description:
 *
 * @author frp
 * @version 1.0
 * @date 2020/12/23 15:42
 */
public class TokenUtil {
    /**
     * 获得指定数目的UUID
     *
     * @param number int 需要获得的UUID数量
     * @return String[] UUID数组
     */
    public static String[] getUUID(int number) {
        if (number < 1) {
            return null;
        }
        String[] retArray = new String[number];
        for (int i = 0; i < number; i++) {
            retArray[i] = getUUID();
        }
        return retArray;
    }

    /**
     * 获得UUID
     * 32位
     * @return String UUID
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid + UUID.randomUUID().toString();
        //去掉“-”符号
        return uuid.replaceAll("-", "");
    }

    /**
     * 生成随机字符
     * @param length 字符长度
     * @return String
     */
    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 生成时间戳
     * @return String
     */
    public static String getTimestamp() {
        Date date = new Date();
        long time = date.getTime();
        return (time + "");
    }

    public static void main(String[] args) {
        System.out.println("zte" + TokenUtil.getTimestamp() + TokenUtil.getRandomString(5));
        System.out.println(TokenUtil.getUUID());
    }
}
