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
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
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

    public static StringBuilder mapToString(Map<String, Object> paramsMap) {
        Set<String> keySet = paramsMap.keySet();
        List<String> paramNames = new ArrayList<>(keySet);
        // 请求参数按参数名升序排序
        Collections.sort(paramNames);
        StringBuilder paramNameValue = new StringBuilder();
        // 拼接参数名及参数值
        for (String paramName : paramNames) {
            paramNameValue.append(paramName).append(paramsMap.get(paramName));
        }
        return paramNameValue;
    }

    public static String generateOpenSign(Map<String, Object> paramsMap, Long appId) {
        StringBuilder paramNameValue = mapToString(paramsMap);
        // 拼接密钥
        String secret = serviceSecret.selectAppSecret(appId);
        if (StringUtils.isBlank(secret)) {
            throw new CommonException(ErrorCode.SECRET_NOT_EXIST);
        }
        String source = secret + paramNameValue.toString() + secret;
        return signMd5(source);
    }

    public static void verify(Map<String, Object> view, Long appId, Long requestTime, String sign) {
        if (Long.valueOf(TokenUtil.getTimestamp()).compareTo((requestTime) + SIGN_DEADLINE) > 0) {
            throw new RuntimeException("请求失效，请重新发起请求");
        }
        if (sign.equals(generateOpenSign(view, appId))) {
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