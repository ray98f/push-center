package com.zte.msg.pushcenter.config;

import com.zte.msg.pushcenter.utils.AesUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.annotation.Resource;

@Configuration
public class BeanConfig {

    @Resource
    private JavaMailConfig javaMailConfig;

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    @Bean
    public JavaMailSenderImpl mailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setProtocol(javaMailConfig.getProtocol());
        javaMailSender.setHost(javaMailConfig.getHost());
        javaMailSender.setPort(javaMailConfig.getPort());
        javaMailSender.setUsername(AesUtils.decrypt(javaMailConfig.getUsername()));
        javaMailSender.setPassword(AesUtils.decrypt(javaMailConfig.getPassword()));
        return javaMailSender;
    }

//    @Bean
//    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
//        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
//        configurer.setIgnoreUnresolvablePlaceholders(true);
//        return configurer;
//    }

}
