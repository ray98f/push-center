package com.zte.msg.pushcenter.pccore.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/1/10 9:41
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.mail")
public class JavaMailConfig {

//    @Value("${spring.mail.host}")
    private String host;

    private Integer port;

    private String protocol;

    private String username;

    private String password;
}
