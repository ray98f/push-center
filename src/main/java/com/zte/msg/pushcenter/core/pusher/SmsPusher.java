package com.zte.msg.pushcenter.core.pusher;

import com.zte.msg.pushcenter.core.BasePusher;
import com.zte.msg.pushcenter.core.pusher.msg.Message;
import com.zte.msg.pushcenter.core.pusher.msg.SmsMessage;
import com.zte.msg.pushcenter.service.SmsTemplateService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/11 9:14
 */
@Service
@Slf4j
public class SmsPusher extends BasePusher {

    @Resource
    private SmsTemplateService smsTemplateService;

    /**
     * 平
     */
    public static Map<Long, TreeMap<Integer, ConfigDetail>> configMap = new HashMap<>();

    @PostConstruct
    public void init() {
        log.info("==========initialize sms push config...==========");
        List<ConfigDetail> configDetails = smsTemplateService.selectConfigDetail();
        configDetails.forEach(this::flushConfigMap);
        log.info("==========initialize sms config completed : {} ==========", configMap.size());
    }

    public void flushConfigMap(ConfigDetail o) {
        TreeMap<Integer, ConfigDetail> treeMap = configMap.get(o.getId());
        if (Objects.isNull(treeMap)) {
            treeMap = new TreeMap<>();
        }
        treeMap.put(o.getOrder(), o);
        configMap.put(o.getId(), treeMap);
    }

    @Override
    public void push(Message message) {
        SmsMessage smsMessage = (SmsMessage) message;

        CompletableFuture.supplyAsync(() -> {
            log.info("==========submit sms push task==========");
            // TODO: 2020/12/31 调用脚本推送

            return null;
        }, pushExecutor).exceptionally(e -> {
            log.error("Error!!! An exception occurred when push a message.");
            // TODO: 2020/12/22 异常的情况返回错误详情
            return null;
        }).thenAcceptAsync(o -> {
            if (message.getIsCallBack()) {
                response(message, null);
            }
        }, resExecutor);
    }

    @Data
    public static class ConfigDetail {

        /**
         * sms_template表id
         */
        private Long id;

        private String name;

        private String example;

        private String sign;

        private Integer order;

        private String sAppId;

        private String secretId;

        private String secretKey;

        private String providerName;
    }

    @Data
    protected static class MessageRecord {

        private String phoneNum;

        private String templateId;

        private String sign;

        private Map<String, String> vars;

    }
}

