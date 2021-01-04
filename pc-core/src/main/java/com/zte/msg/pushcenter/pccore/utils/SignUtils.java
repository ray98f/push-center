package com.zte.msg.pushcenter.pccore.utils;

import com.zte.msg.pushcenter.pccore.dto.SignView;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

public class SignUtils {

    public static String MD5(String str) {
        return AesUtils.encrypt(DigestUtils.md5DigestAsHex(str.getBytes(StandardCharsets.UTF_8)));
    }

    public static boolean verify(String str, SignView signView) {
        String s = signView.toString();
        return str.equals(MD5(s));
    }
}
