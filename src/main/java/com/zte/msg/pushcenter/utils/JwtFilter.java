package com.zte.msg.pushcenter.utils;

import com.zte.msg.pushcenter.dto.TokenInfo;
import com.zte.msg.pushcenter.enums.ErrorCode;
import com.zte.msg.pushcenter.enums.TokenStatus;
import com.zte.msg.pushcenter.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    private final TokenUtil tokenUtil = new TokenUtil();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        try {
            for (String page : excludedPages) {
                if (httpRequest.getRequestURI().equals(page)) {
                    chain.doFilter(httpRequest, httpResponse);
                    return;
                }
            }
            String token = httpRequest.getHeader("Authorization");
            TokenStatus tokenStatus = tokenUtil.verifyToken(token);
            switch (tokenStatus) {
                //有效
                case VALID:
                    TokenInfo tokenInfo = TokenUtil.getTokenInfo(token);
                    httpRequest.setAttribute("tokenInfo", tokenInfo);
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

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("jwtFilter init ...");
    }

    @Override
    public void destroy() {
        log.info("jwtFilter destroy ...");
    }
}
