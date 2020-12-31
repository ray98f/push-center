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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * templateId作为key
     */
    public static Map<String, ConfigDetail> configMap = new HashMap<>();

    @PostConstruct
    public void init() {
        log.info("==========initialize sms push config...==========");
        List<ConfigDetail> configDetails = smsTemplateService.selectConfigDetail();
        configDetails.forEach(o -> configMap.put(o.getTemplateId(), o));
        log.info("==========initialize sms config completed : {}==========", configMap.size());
    }

    @Override
    public void push(Message message) {
        SmsMessage smsMessage = (SmsMessage) message;
        ConfigDetail configDetail = configMap.get(smsMessage.getTemplateId());

    }

    @Data
    public static class ConfigDetail {

        private String templateId;

        private Integer type;

        private Long configId;

        private Long smsConfigId;

        private Long scriptId;

        private String configName;

        private String providerName;

        private String context;

        private String scriptName;

        private String scriptTag;

        private String smsConfigName;

        private String sAppId;

        private String secretId;

        private String secretKey;

        private String example;

        private String sign;
    }

    @Data
    protected static class MessageRecord {

        private String phoneNum;

        private String templateId;

        private String sign;

        private Map<String, String> vars;

    }
}
