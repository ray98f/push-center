package com.zte.msg.pushcenter.pccore.config.filter;

import com.zte.msg.pushcenter.pccore.config.RequestHeaderContext;
import com.zte.msg.pushcenter.pccore.dto.SimpleTokenInfo;
import com.zte.msg.pushcenter.pccore.enums.ErrorCode;
import com.zte.msg.pushcenter.pccore.enums.TokenStatus;
import com.zte.msg.pushcenter.pccore.exception.CommonException;
import com.zte.msg.pushcenter.pccore.utils.Constants;
import com.zte.msg.pushcenter.pccore.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

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
public class JwtFilter implements Filter {

    /**
     * 排除拦截的请求
     */
    private final String[] excludedPages = {
            "/api/v1/login",
            "/api/v1/openapi/token"
    };

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String openApiPages = Constants.OPENAPI_URL;
        if (httpRequest.getRequestURI().contains(openApiPages) || Arrays.asList(excludedPages).contains(httpRequest.getRequestURI())) {
            chain.doFilter(httpRequest, httpResponse);
        } else {
            String token = httpRequest.getHeader("Authorization");
            if (token == null || StringUtils.isBlank(token)) {
                request.setAttribute("filter.error", new CommonException(ErrorCode.AUTHORIZATION_EMPTY));
                request.getRequestDispatcher("/error/exthrow").forward(request, response);
                return;
            }
            TokenStatus tokenStatus = TokenUtil.verifySimpleToken(token);
            switch (Objects.requireNonNull(tokenStatus)) {
                //有效
                case VALID:
                    SimpleTokenInfo simpleTokenInfo = TokenUtil.getSimpleTokenInfo(token);
                    new RequestHeaderContext.RequestHeaderContextBuild().user(simpleTokenInfo).build();
                    httpRequest.setAttribute("tokenInfo", simpleTokenInfo);
                    chain.doFilter(httpRequest, httpResponse);
                    break;
                //过期
                case EXPIRED:
                    request.setAttribute("filter.error", new CommonException(ErrorCode.AUTHORIZATION_IS_OVERDUE));
                    request.getRequestDispatcher("/error/exthrow").forward(request, response);
                    break;
                //无效
                default:
                    request.setAttribute("filter.error", new CommonException(ErrorCode.AUTHORIZATION_INVALID));
                    request.getRequestDispatcher("/error/exthrow").forward(request, response);
                    break;
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