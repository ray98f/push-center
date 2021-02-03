package com.zte.msg.pushcenter.pccore.utils;

import com.alibaba.fastjson.JSON;
import com.zte.msg.pushcenter.pccore.dto.req.SmsMessageReqDTO;
import com.zte.msg.pushcenter.pccore.enums.ErrorCode;
import com.zte.msg.pushcenter.pccore.exception.CommonException;
import com.zte.msg.pushcenter.pccore.service.AppService;
import com.zte.msg.pushcenter.pccore.service.SecretService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author frp
 */
@Slf4j
@Component
public class SignUtils {

    public static final int SIGN_DEADLINE = 5 * 60 * 1000;
    public static final String SEMICOLON = ";";

    @Autowired
    private SecretService secretService;

    @Autowired
    private AppService appService;

    private static SecretService serviceSecret;
    private static AppService serviceApp;

    @PostConstruct
    public void init() {
        if (serviceSecret == null && serviceApp == null) {
            setDataSource(secretService, appService);
        }
    }

    private synchronized static void setDataSource(SecretService secretService, AppService appService) {
        serviceSecret = secretService;
        serviceApp = appService;
    }

    private static String signMd5(String str) {
        return DigestUtils.md5DigestAsHex(str.getBytes(StandardCharsets.UTF_8));
    }

    public static String generateOpenSign(Object view, Long appId, Long requestTime) {
        String s = JSON.toJSONString(view) + appId + requestTime;
        String secret = serviceSecret.selectAppSecret(appId);
        if (StringUtils.isBlank(secret)) {
            throw new CommonException(ErrorCode.SECRET_NOT_EXIST);
        }
//        s = s + String.join(SEMICOLON, serviceApp.selectWhiteIp(appId));
        s = secret + s + secret;
        return signMd5(s);
    }

    public static void verify(Object view, Long appId, Long requestTime, String sign) {
        if (Long.valueOf(TokenUtil.getTimestamp()).compareTo((requestTime) + SIGN_DEADLINE) > 0) {
            throw new RuntimeException("请求失效，请重新发起请求");
        }
        if (sign.equals(generateOpenSign(view, appId, requestTime))) {
            log.info("签名校验通过");
        } else {
            throw new CommonException(ErrorCode.OPENAPI_VERIFY_FAIL);
        }
    }

    public static void main(String[] args) {
        SmsMessageReqDTO smsMessageReqDTO = new SmsMessageReqDTO();
        smsMessageReqDTO.setTemplateId(1L);
        smsMessageReqDTO.setPhoneNum(new String[]{"1111", "2222"});
        Map<String, String> data = new HashMap<>(16);
        data.put("aa", "deadened");
        data.put("bb", "referrer");
        data.put("cc", "trotter");
        smsMessageReqDTO.setVars(data);
        String s = JSON.toJSONString(smsMessageReqDTO);
        System.out.println(s);
    }
}