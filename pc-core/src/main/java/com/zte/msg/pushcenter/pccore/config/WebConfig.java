package com.zte.msg.pushcenter.pccore.config;

import com.zte.msg.pushcenter.pccore.config.filter.CorsFilter;
import com.zte.msg.pushcenter.pccore.config.filter.JwtFilter;
import com.zte.msg.pushcenter.pccore.config.interceptor.HttpRequestInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

/**
 * 配置类
 *
 * @author frp
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Resource
    private HttpRequestInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
                .addPathPatterns("/api/*")
                .excludePathPatterns("/api/vi/login")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
    }

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilterRegistration() {
        FilterRegistrationBean<JwtFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new JwtFilter());
        registration.setName("JwtFilter");
        registration.addUrlPatterns("/api");
        registration.setOrder(2);
        return registration;
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterRegistration() {
        FilterRegistrationBean<CorsFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new CorsFilter());
        registration.setName("CorsFilter");
        registration.addUrlPatterns("/*");
        registration.setOrder(1);
        return registration;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE", "PATCH")
                .allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept", "x-auth-token")
                .allowCredentials(false).maxAge(3600);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
