package com.zte.msg.pushcenter.pccore.core.pusher;

import com.alibaba.fastjson.JSONObject;
import com.zte.msg.pushcenter.pccore.core.pusher.msg.Message;
import com.zte.msg.pushcenter.pccore.core.pusher.msg.SmsMessage;
import com.zte.msg.pushcenter.pccore.entity.SmsInfo;
import com.zte.msg.pushcenter.pccore.service.SmsService;
import com.zte.msg.pushcenter.pccore.utils.AesUtils;
import com.zte.msg.pushcenter.pccore.utils.MapUtils;
import com.zte.msg.pushcenter.pcscript.Script;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.sql.Timestamp;
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
    private SmsService smsService;

    /**
     * 模版id作为key，order作为内部嵌套map的key
     */
    public static Map<Long, TreeMap<Integer, ConfigDetail>> smsConfigMap = new HashMap<>();

//    public static CopyOnWriteArrayList<SmsInfo> messageInfos = new CopyOnWriteArrayList<>(new ArrayList<>(30));

    @PostConstruct
    public void init() {
        log.info("==========initialize sms push config...==========");
        List<ConfigDetail> configDetails = smsService.selectAllSmsConfigForInit();
        configDetails.forEach(this::flushConfigMap);
        log.info("==========initialize sms config completed : {} ==========", smsConfigMap.size());
    }

    public void flushConfigMap(ConfigDetail o) {
        TreeMap<Integer, ConfigDetail> treeMap = smsConfigMap.get(o.getId());
        if (Objects.isNull(treeMap)) {
            treeMap = new TreeMap<>();
        }
        if (Objects.nonNull(o.getSecretId())) {
            o.setSecretId(AesUtils.decrypt(o.getSecretId()));
        }
        if (Objects.nonNull(o.getSecretKey())) {
            o.setSecretKey(AesUtils.decrypt(o.getSecretKey()));
        }
        treeMap.put(o.getOrder(), o);
        smsConfigMap.put(o.getId(), treeMap);
    }

    @Override
    public void push(Message message) {
        Timestamp transmitTime = new Timestamp(System.currentTimeMillis());
        SmsMessage smsMessage = (SmsMessage) message;
        TreeMap<Integer, ConfigDetail> treeMap = smsConfigMap.get(smsMessage.getTemplateId());
        ConfigDetail configDetail = treeMap.firstEntry().getValue();
        for (String phoneNum : smsMessage.getPhoneNum()) {
            CompletableFuture.supplyAsync(() -> {
                log.info("==========submit sms push task==========");
                Map<String, Object> mapAll = new HashMap<>();
                mapAll.putAll(MapUtils.objectToMap(configDetail));
                mapAll.putAll(MapUtils.objectToMap(smsMessage));
                mapAll.put("phoneNum", phoneNum);
                Script scriptClass = scriptManager.getScriptClass(configDetail.getScriptTag());
                return scriptClass.execute(mapAll);
            }, pushExecutor).exceptionally(e -> {
                log.error("Error!!! An exception occurred when push a message.");
                e.printStackTrace();
                return "error";
            }).thenAcceptAsync(o -> {
                if (message.getIsCallBack()) {
                    response(message, JSONObject.parseObject(o));
                }
                SmsInfo smsInfo = new SmsInfo();
                smsInfo.setTransmitTime(transmitTime);
                smsInfo.setAppId(smsMessage.getAppId());
//            smsInfo.setContent(configDetail);
                if ("error".equals(o)) {

                }
                persist();
            }, resExecutor);
        }
    }

    @Override
    protected void persist() {

        System.out.println("-------------- Sms message persist-------------------");

    }

    @Data
    public static class ConfigDetail {

        /**
         * sms_template表id
         */
        private Long id;

        /**
         * 供应商的templateId
         */
        private String sTemplateId;

        private String name;

        private String example;

        private String sign;

        private Integer order;

        private String sAppId;

        private String secretId;

        private String secretKey;

        private String providerName;

        private String scriptTag;
    }

    @Data
    protected static class MessageRecord {

        private String phoneNum;

        private String templateId;

        private String sign;

        private Map<String, String> vars;

    }
}

