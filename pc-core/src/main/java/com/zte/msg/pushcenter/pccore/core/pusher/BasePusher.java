package com.zte.msg.pushcenter.pccore.core.pusher;

import com.alibaba.fastjson.JSONObject;
import com.zte.msg.pushcenter.pccore.core.javac.CodeJavac;
import com.zte.msg.pushcenter.pccore.core.pusher.msg.Message;
import com.zte.msg.pushcenter.pccore.dto.DataResponse;
import com.zte.msg.pushcenter.pccore.service.HistoryService;
import com.zte.msg.pushcenter.pcscript.PcScript;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
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
@Service
public abstract class BasePusher {

    @Resource
    protected HistoryService historyService;

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.consumer.topic}")
    private String kafkaTopic;

    @Resource(name = "asyncPushExecutor")
    protected ThreadPoolTaskExecutor pushExecutor;

    @Resource(name = "asyncResponseExecutor")
    protected ThreadPoolTaskExecutor resExecutor;

    @Resource
    protected RestTemplate restTemplate;

    @Resource
    protected CodeJavac scriptManager;

    /**
     * 提交推送
     *
     * @param message
     * @return
     */
    public final void submit(Message message) {
        kafkaTemplate.send(kafkaTopic, JSONObject.toJSONString(message));
    }


    public abstract void push(Message message);

    /**
     * 回调
     *
     * @param message
     */
    protected void response(Message message, PcScript.Res res) {
        if (!message.getIsCallBack()) {
            return;
        }
        log.info("==========start sms push response==========");
        try {
            restTemplate.exchange(message.getCallBackUrl(), HttpMethod.POST, new HttpEntity<>(DataResponse.of(res)), String.class);
        } catch (Exception e) {
            log.error("Error!!! An exception occurred when response.");
        }
    }

    /**
     * 消息记录存库
     */
//    @Scheduled(cron = "0 0/1 * * * ? ")
    abstract protected void persist(Message message, PcScript.Res res);
}
