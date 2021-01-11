package com.zte.msg.pushcenter.pccore.core.pusher;

import com.alibaba.fastjson.JSONObject;
import com.zte.msg.pushcenter.pccore.core.pusher.msg.Message;
import com.zte.msg.pushcenter.pccore.core.pusher.msg.SmsMessage;
import com.zte.msg.pushcenter.pccore.entity.SmsInfo;
import com.zte.msg.pushcenter.pccore.model.SmsConfigDetailModel;
import com.zte.msg.pushcenter.pccore.service.SmsService;
import com.zte.msg.pushcenter.pccore.utils.MapUtils;
import com.zte.msg.pushcenter.pcscript.PcScript;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Method;
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
        List<SmsConfigDetailModel> configDetails = smsService.getAllSmsConfigForInit();
        configDetails.forEach(o -> {
            ConfigDetail configDetail = new ConfigDetail();
            BeanUtils.copyProperties(o, configDetail);
            configDetail.setConfig(JSONObject.parseObject(o.getConfig()));
            flushConfigMap(configDetail);
        });
        log.info("==========initialize sms config completed : {} ==========", smsConfigMap.size());
    }

    public void flushConfigMap(ConfigDetail o) {
        TreeMap<Integer, ConfigDetail> treeMap = smsConfigMap.get(o.getSmsTemplateId());
        if (Objects.isNull(treeMap)) {
            treeMap = new TreeMap<>();
        }
        treeMap.put(o.getPriority(), o);
        smsConfigMap.put(o.getSmsTemplateId(), treeMap);
    }

    @Override
    public void push(Message message) {
        long pushStart = System.currentTimeMillis();
        SmsMessage smsMessage = (SmsMessage) message;
        smsMessage.setTransmitTime(new Timestamp(pushStart));
        TreeMap<Integer, ConfigDetail> treeMap = smsConfigMap.get(smsMessage.getTemplateId());
        ConfigDetail configDetail = treeMap.lastEntry().getValue();
        smsMessage.setContent(configDetail.getContent());
        smsMessage.setProviderName(configDetail.getProviderName());
        smsMessage.setCode(configDetail.getCode());
        Map<String, Object> mapAll = new HashMap<>();
        mapAll.putAll(MapUtils.objectToMap(configDetail));
        mapAll.putAll(MapUtils.objectToMap(smsMessage));
        mapAll.putAll(configDetail.getConfig().getInnerMap());
        for (int i = 0; i < smsMessage.getPhoneNum().length; i++) {
            String phoneNum = smsMessage.getPhoneNum()[i];
            mapAll.put("phoneNum", phoneNum);
            smsMessage.setIndex(i);
            CompletableFuture.supplyAsync(() -> {
                log.info("==========submit sms push task==========");

                PcScript.Res res = null;
                try {
                    Class<?> scriptClass = scriptManager.getScriptClass(configDetail.getScriptTag());
                    Method execute = scriptClass.getMethod("execute", Map.class);
                    Object o = scriptClass.newInstance();
                    res = (PcScript.Res) execute.invoke(o, mapAll);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return res;
            }, pushExecutor).exceptionally(e -> {
                log.error("Error!!! An exception occurred when push a message.");
                e.printStackTrace();
                return new PcScript.Res(1, "系统内部错误");
            }).thenAcceptAsync(o -> {
                if (message.getIsCallBack()) {
                    response(message, o);
                }
                persist(smsMessage, o);
            }, resExecutor);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected void persist(Message message, PcScript.Res res) {

        SmsMessage smsMessage = (SmsMessage) message;
        SmsInfo smsInfo = new SmsInfo(smsMessage, res);
        historyService.addHistorySms(smsInfo);
        System.out.println("-------------- Sms message persist-------------------");

    }

    @Data
    public static class ConfigDetail {

        private long smsTemplateId;

        private long platformTemplateId;

        private int priority;

        private int templateStatus;

        private String code;

        private String sign;

        private String content;

        private int platformTemplateStatus;

        private String providerName;

        private int type;

        private String scriptTag;

        private JSONObject config;

    }
}

