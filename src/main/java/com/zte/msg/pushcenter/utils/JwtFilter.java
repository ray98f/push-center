package com.zte.msg.pushcenter.utils;

import com.zte.msg.pushcenter.dto.OpenApiTokenInfo;
import com.zte.msg.pushcenter.dto.SimpleTokenInfo;
import com.zte.msg.pushcenter.enums.ErrorCode;
import com.zte.msg.pushcenter.enums.TokenStatus;
import com.zte.msg.pushcenter.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

/**
 * jwt过滤器
 *
 * @author frp
 */
@Slf4j
@Component
public class JwtFilter implements Filter {

    /**
     * 排除拦截的请求
     */
    private final String[] excludedPages = {"/login"};

    private final String[] openApiPages = {"/api/v1/openapi/token"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        if (Arrays.asList(openApiPages).contains(httpRequest.getRequestURI())) {
            try {
                String token = httpRequest.getHeader("Authorization");
                TokenStatus tokenStatus = TokenUtil.verifyOpenApiToken(token);
                switch (Objects.requireNonNull(tokenStatus)) {
                    //有效
                    case VALID:
                        OpenApiTokenInfo openApiTokenInfo = TokenUtil.getOpenApiTokenInfo(token);
                        httpRequest.setAttribute("tokenInfo", openApiTokenInfo);
                        chain.doFilter(httpRequest, httpResponse);
                        break;
                    //过期
                    case EXPIRED:
                        throw new CommonException(ErrorCode.AUTHORIZATION_IS_OVERDUE);
                        //无效
                    default:
                        throw new CommonException(ErrorCode.AUTHORIZATION_INVALID);
                }
            } catch (Exception e) {
                log.error("jwtFilter Exception: {}", e.getMessage());
                throw new CommonException(ErrorCode.AUTHORIZATION_CHECK_FAIL);
            }
        } else if (Arrays.asList(excludedPages).contains(httpRequest.getRequestURI())) {
            try {
                chain.doFilter(httpRequest, httpResponse);
            } catch (IOException | ServletException e) {
                e.printStackTrace();
            }
        } else {
            try {
                String token = httpRequest.getHeader("Authorization");
                TokenStatus tokenStatus = TokenUtil.verifySimpleToken(token);
                switch (Objects.requireNonNull(tokenStatus)) {
                    //有效
                    case VALID:
                        SimpleTokenInfo simpleTokenInfo = TokenUtil.getSimpleTokenInfo(token);
                        httpRequest.setAttribute("tokenInfo", simpleTokenInfo);
                        chain.doFilter(httpRequest, httpResponse);
                        break;
                    //过期
                    case EXPIRED:
                        throw new CommonException(ErrorCode.AUTHORIZATION_IS_OVERDUE);
                        //无效
                    default:
                        throw new CommonException(ErrorCode.AUTHORIZATION_INVALID);
                }
            } catch (Exception e) {
                log.error("jwtFilter Exception: {}", e.getMessage());
                throw new CommonException(ErrorCode.AUTHORIZATION_CHECK_FAIL);
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("jwtFilter init ...");
    }

    @Override
    public void destroy() {
        log.info("jwtFilter destroy ...");
    }
}