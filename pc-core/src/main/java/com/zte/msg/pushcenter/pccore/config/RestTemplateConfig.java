package com.zte.msg.pushcenter.pccore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/5/13 15:56
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplateFactory() {
        return new RestTemplate();
    }
}
