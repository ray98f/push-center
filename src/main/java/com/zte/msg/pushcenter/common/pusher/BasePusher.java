package com.zte.msg.pushcenter.common.pusher;

import com.alibaba.fastjson.JSONObject;
import com.zte.msg.pushcenter.msg.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/11 8:33
 */
@Slf4j
public abstract class BasePusher {

    @Resource(name = "asyncExecutor")
    protected ThreadPoolTaskExecutor executor;

    @Resource
    protected RestTemplate restTemplate;

    /**
     * 推送
     *
     * @param message
     * @return
     */
    public abstract CompletableFuture<JSONObject> push(Message message);


}
