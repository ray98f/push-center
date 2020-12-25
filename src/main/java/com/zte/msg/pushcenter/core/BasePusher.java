package com.zte.msg.pushcenter.core;

import com.alibaba.fastjson.JSONObject;
import com.zte.msg.pushcenter.core.pusher.msg.Message;
import com.zte.msg.pushcenter.dto.DataResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
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
    public void submit(Message message) {

        CompletableFuture.supplyAsync(() -> {
            log.info("==========submit sms push task==========");
            return message.push();
        }, pushExecutor).exceptionally(e -> {
            log.error("Error!!! An exception occurred when push a message.");
            // TODO: 2020/12/22 异常的情况返回错误详情
            return null;
        }).thenAcceptAsync(o -> {
            if (message.getIsCallBack()) {
                response(o, message.getCallBackUrl());
            }
        }, resExecutor);
    }


    /**
     * 回调
     *
     * @param res
     */
    public void response(JSONObject res, String url) {
        log.info("==========start sms push response==========");
        try {
            restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(DataResponse.of(res)), String.class);
        } catch (Exception e) {
            log.error("Error!!! An exception occurred when response.");
        }
    }
}
