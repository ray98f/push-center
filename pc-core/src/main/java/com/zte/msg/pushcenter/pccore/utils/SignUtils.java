package com.zte.msg.pushcenter.pccore.utils;

import com.alibaba.fastjson.JSON;
import com.zte.msg.pushcenter.pccore.enums.ErrorCode;
import com.zte.msg.pushcenter.pccore.exception.CommonException;
import com.zte.msg.pushcenter.pccore.service.SecretService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * @author frp
 */
@Slf4j
public class SignUtils {

    public static final int SIGN_DEADLINE = 5 * 60 * 1000;

    @Resource
    private static SecretService secretService;

    private static String md5(String str) {
        return DigestUtils.md5DigestAsHex(str.getBytes(StandardCharsets.UTF_8));
    }

    public static void verify(Object view, Integer appId, String requestTime, String sign) {
        if (Long.valueOf(TokenUtil.getTimestamp()).compareTo(Long.parseLong(requestTime) + SIGN_DEADLINE) > 0) {
            log.error("请求失效，请重新发起请求");
            throw new RuntimeException("请求失效，请重新发起请求");
        }

        String s = JSON.toJSONString(view) + appId + requestTime;
        String secret = secretService.selectAppSecret(appId);
        s = secret + s + secret;
        if (sign.equals(md5(s))) {
            log.info("签名校验通过");
        } else {
            log.error("OpenApi 签名校验失败");
            throw new CommonException(ErrorCode.ENUM_VALUE_ERROR);
        }
    }
}