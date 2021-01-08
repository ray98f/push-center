package com.zte.msg.pushcenter.pccore.config.interceptor;

import com.zte.msg.pushcenter.pccore.config.RequestHeaderContext;
import com.zte.msg.pushcenter.pccore.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/4/29 16:27
 */
@Slf4j
@Component
public class HttpRequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        initHeaderContext(request);
        return true;
    }

    private void initHeaderContext(HttpServletRequest request) {
        try {
            new RequestHeaderContext.RequestHeaderContextBuild()
                    .user(URLDecoder.decode(request.getHeader(Constants.AUTHORIZATION), "UTF-8"))
                    .build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) {

        RequestHeaderContext.clean();
    }

}
