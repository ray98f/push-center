package com.zte.msg.pushcenter.pccore.utils;

import com.zte.msg.pushcenter.pccore.dto.SignView;
import com.zte.msg.pushcenter.pccore.enums.ErrorCode;
import com.zte.msg.pushcenter.pccore.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

@Slf4j
public class SignUtils {

    @Resource
    private static HttpServletRequest request;

    private static String MD5(String str) {
        return DigestUtils.md5DigestAsHex(str.getBytes(StandardCharsets.UTF_8));
    }

    private static SignView view(Object view, String appId) {
        SignView signView = new SignView();
        signView.setSendMsg(view);
        signView.setAppId(appId);
        signView.setNow(request.getHeader("RequestTime"));
        signView.setUrl(request.getRequestURI());
        signView.setSign(request.getHeader("Authorization"));
        return signView;
    }

    public static void verify(Object view, String appId) {
        if (Long.valueOf(TokenUtil.getTimestamp()).compareTo(Long.parseLong(request.getHeader("time")) + 5 * 60 * 1000) > 0) {
            log.error("请求超时，请重新发起请求");
            throw new RuntimeException("请求超时，请重新发起请求");
        }
        SignView signView = view(view, appId);
        String s = signView.toString();
        if (signView.getSign().equals(MD5(s))) {
            log.info("签名校验通过");
        } else {
            log.error("OpenApi 签名校验失败");
            throw new CommonException(ErrorCode.ENUM_VALUE_ERROR);
        }
    }
}