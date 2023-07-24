package com.zte.msg.pushcenter.pccore.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zte.msg.pushcenter.pccore.dto.req.SmsMessageReqDTO;
import com.zte.msg.pushcenter.pccore.enums.ErrorCode;
import com.zte.msg.pushcenter.pccore.exception.CommonException;
import com.zte.msg.pushcenter.pccore.service.SecretService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author frp
 */
@Slf4j
@Component
public class SignUtils {

    public static final int SIGN_DEADLINE = 5 * 60 * 1000;
    public static final String SPACE = " ";
    public static final String EMPTY = "";

    @Resource
    private SecretService secretService;

    private static SecretService service;

    @PostConstruct
    public void init() {
        if (service == null) {
            setDataSource(secretService);
        }
    }

    private synchronized static void setDataSource(SecretService secretService) {
        service = secretService;
    }

    private static String signMd5(String str) {
        return DigestUtils.md5DigestAsHex(str.getBytes(StandardCharsets.UTF_8)).toUpperCase();
    }

    public static StringBuilder mapToString(Map<String, Object> paramsMap) {
        log.info("mapToString:" + JSONObject.toJSONString(paramsMap));
        Set<String> keySet = paramsMap.keySet();
        List<String> paramNames = new ArrayList<>(keySet);
        // 请求参数按参数名升序排序
        Collections.sort(paramNames);
        StringBuilder paramNameValue = new StringBuilder();
        // 拼接参数名及参数值
        for (String paramName : paramNames) {
            if (Objects.isNull(paramsMap.get(paramName))) continue;
            if (paramsMap.get(paramName) instanceof Map){
                Map<String, Object> map = new TreeMap<>((HashMap<String, Object>) paramsMap.get(paramName));
                paramNameValue.append(paramName).append(map);
                continue;
            }
            if (paramsMap.get(paramName).getClass().isArray()) {
                Object[] array = (Object[]) paramsMap.get(paramName);
                Arrays.sort(array);
                paramNameValue.append(paramName).append(Arrays.toString(array));
                continue;
            }
            paramNameValue.append(paramName).append(paramsMap.get(paramName));
        }
        return paramNameValue;
    }

    public static String generateOpenSign(Map<String, Object> paramsMap, Long appId) {
        StringBuilder paramNameValue = mapToString(paramsMap);
        // 拼接密钥
        String secret = service.selectAppSecret(appId);
        if (StringUtils.isBlank(secret)) {
            throw new CommonException(ErrorCode.SECRET_NOT_EXIST);
        }
        String source = (secret + paramNameValue.toString() + secret).replaceAll(SPACE, EMPTY);
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