package com.zte.msg.pushcenter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/11 15:16
 */
@Configuration
public class AsyncExecutorConfig {

    @Bean(name = "asyncPushExecutor")
    public ThreadPoolTaskExecutor asyncPushExecutor() {
        return buildExecutor(8, 20, 200, "asyncPushExecutor-");
    }

    @Bean(name = "asyncResponseExecutor")
    public ThreadPoolTaskExecutor asyncResponseExecutor() {
        return buildExecutor(5, 10, 100, "asyncResponseExecutor-");
    }

    private ThreadPoolTaskExecutor buildExecutor(int corePoolSize,
                                                 int maxPoolSize,
                                                 int queueCapacity,
                                                 String name) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(30);
        executor.setThreadNamePrefix(name);
        executor.setAllowCoreThreadTimeOut(true);
        executor.initialize();
        return executor;
    }
}
