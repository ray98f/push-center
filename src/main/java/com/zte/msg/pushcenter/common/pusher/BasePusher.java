package com.zte.msg.pushcenter.common.pusher;

import com.alibaba.fastjson.JSONObject;
import com.zte.msg.pushcenter.common.pusher.msg.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/11 8:33
 */
@Slf4j
public abstract class BasePusher {

    @Resource(name = "asyncPushExecutor")
    protected ThreadPoolTaskExecutor pushExecutor;

    @Resource(name = "asyncResponseExecutor")
    protected ThreadPoolTaskExecutor resExecutor;

    @Resource
    protected RestTemplate restTemplate;

    /**
     * 提交推送
     *
     * @param message
     * @return
     */
    public abstract void submit(Message message);

    /**
     * 回调
     *
     * @param res
     */
    public abstract void response(JSONObject res);


}
