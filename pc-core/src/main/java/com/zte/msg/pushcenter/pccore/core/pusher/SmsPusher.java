package com.zte.msg.pushcenter.pccore.core.pusher;

import com.alibaba.fastjson.JSONObject;
import com.zte.msg.pushcenter.pccore.core.pusher.base.BasePusher;
import com.zte.msg.pushcenter.pccore.core.pusher.base.Config;
import com.zte.msg.pushcenter.pccore.core.pusher.base.Message;
import com.zte.msg.pushcenter.pccore.core.pusher.msg.SmsMessage;
import com.zte.msg.pushcenter.pccore.entity.Provider;
import com.zte.msg.pushcenter.pccore.entity.SmsInfo;
import com.zte.msg.pushcenter.pccore.enums.ErrorCode;
import com.zte.msg.pushcenter.pccore.enums.PushMethods;
import com.zte.msg.pushcenter.pccore.exception.CommonException;
import com.zte.msg.pushcenter.pccore.mapper.SmsTemplateRelationMapper;
import com.zte.msg.pushcenter.pccore.model.SmsConfigModel;
import com.zte.msg.pushcenter.pccore.model.SmsInfoModel;
import com.zte.msg.pushcenter.pccore.service.AppService;
import com.zte.msg.pushcenter.pccore.utils.MapUtils;
import com.zte.msg.pushcenter.pccore.utils.PatternUtils;
import com.zte.msg.pushcenter.pcscript.ParamConstants;
import com.zte.msg.pushcenter.pcscript.PcScript;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/11 9:14
 */
@SuppressFBWarnings("BC_UNCONFIRMED_CAST")
@Service
@Slf4j
public class SmsPusher extends BasePusher {

    @Resource
    private AppService appService;

    @Resource
    private SmsTemplateRelationMapper smsTemplateRelationMapper;
    @Override
    public void init() {
        configMap.put(PushMethods.SMS, new HashMap<>(16));
        List<SmsConfigModel> configDetails = smsTemplateRelationMapper.selectAllSmsConfigForInit();
        buildAndFlush(configDetails);
        log.info("==========initialize sms config completed : {} ==========", configDetails.size());
    }

    private void buildAndFlush(List<SmsConfigModel> smsConfigModels) {
        smsConfigModels.forEach(o -> {
            SmsConfig smsConfig = new SmsConfig();
            BeanUtils.copyProperties(o, smsConfig);
            smsConfig.setParams(PatternUtils.getParams(smsConfig.getContent()));
            smsConfig.setConfig(JSONObject.parseObject(o.getConfig()));
            flushConfig(smsConfig);
        });
        log.info("==========update sms config completed, update count {} ==========", smsConfigModels.size());
    }

    private void flushConfig(SmsConfig o) {
        TreeMap<Integer, Config> treeMap = configMap.get(PushMethods.SMS).get(o.getSmsTemplateId());
        if (Objects.isNull(treeMap)) {
            treeMap = new TreeMap<>();
        }
        treeMap.put(o.getPriority(), o);
        configMap.get(PushMethods.SMS).put(o.getSmsTemplateId(), treeMap);
    }

    @Override
    public void push(Message message) {
        long pushStart = System.currentTimeMillis();
        SmsMessage smsMessage = (SmsMessage) message;
        smsMessage.setTransmitTime(new Timestamp(pushStart));
        TreeMap<Integer, Config> treeMap = configMap.get(PushMethods.SMS).get(smsMessage.getTemplateId());
        SmsConfig smsConfigDetail = (SmsConfig) treeMap.lastEntry().getValue();
        smsMessage.setContent(smsConfigDetail.getContent());
        smsMessage.setProviderName(smsConfigDetail.getProviderName());
        smsMessage.setCode(smsConfigDetail.getCode());
        String[] varsKeySet = smsMessage.getVars().keySet().toArray(new String[]{});
        if (varsKeySet.length != smsConfigDetail.getParams().size()) {
            log.error("模版: {} 参数列表数量不匹配，短信发送失败..", smsMessage.getTemplateId());
            throw new CommonException(ErrorCode.SMS_TEMPLATE_PARAMS_NOT_MATCH);
        }
        for (int i = 0; i < smsConfigDetail.getParams().size(); i++) {
            smsMessage.getVars().put(smsConfigDetail.getParams().get(i), smsMessage.getVars().remove(varsKeySet[i]));
        }
        Map<String, Object> mapAll = new HashMap<>(16);
        mapAll.putAll(MapUtils.objectToMap(smsConfigDetail));
        mapAll.putAll(MapUtils.objectToMap(smsMessage));
        mapAll.putAll(smsConfigDetail.getConfig().getInnerMap());
        for (int i = 0; i < smsMessage.getPhoneNum().length; i++) {
            String phoneNum = smsMessage.getPhoneNum()[i];
            mapAll.put(ParamConstants.PHONE_NUM, phoneNum);
            smsMessage.setIndex(i);
            CompletableFuture.supplyAsync(() -> {
                log.info("==========submit sms push task==========");
                PcScript.Res res = null;
                try {
                    Class<?> scriptClass = scriptManager.getScriptClass(smsConfigDetail.getScriptTag());
                    Method execute = scriptClass.getMethod("execute", Map.class);
                    Object o = scriptClass.newInstance();
                    res = (PcScript.Res) execute.invoke(o, mapAll);
                } catch (Exception e) {
                    res = new PcScript.Res(1, "系统内部错误");
                    e.printStackTrace();
                }
                return res;
            }, pushExecutor).exceptionally(e -> {
                log.error("Error while send a sms message: {}", e.getMessage());
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
        try {
            SmsMessage smsMessage = (SmsMessage) message;
            smsMessage.setAppName(appService.getAppName(smsMessage.getAppId()));
            SmsInfoModel smsInfoModel = new SmsInfoModel(smsMessage, res);
            SmsInfo smsInfo = new SmsInfo();
            BeanUtils.copyProperties(smsInfoModel, smsInfo);
            historyService.addHistorySms(smsInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("========== Sms message persist ==========");
    }

    public void flushConfig(Provider provider) {
        flushConfig(provider, false);
    }

    public void flushConfig(Provider provider, boolean remove) {
        flushConfig(Collections.singletonList(provider), remove);
    }

    public void flushConfig(List<Provider> providers) {
        flushConfig(providers, false);
    }

    public void flushConfig(List<Provider> providers, boolean remove) {
        List<SmsConfigModel> smsConfigForFlush = smsTemplateRelationMapper.selectSmsConfigForFlushByProviderIds(providers
                .stream().map(Provider::getId).collect(Collectors.toList()));
        if (!remove) {
            buildAndFlush(smsConfigForFlush);
        } else {
            removeConfig(smsConfigForFlush.stream()
                    .map(SmsConfigModel::getSmsTemplateId)
                    .collect(Collectors.toList()).toArray(new Long[]{}));
            log.info("========== delete sms config completed, delete count: {} ==========", providers.size());
        }
    }

    public void flushConfig(Long templateId) {
        flushConfig(templateId, false);
    }

    public void flushConfig(Long templateId, boolean remove) {
        flushConfig(new Long[]{templateId}, remove);
    }

    public void flushConfig(Long[] templateIds) {
        flushConfig(templateIds, false);
    }

    public void flushConfig(Long[] templateIds, boolean remove) {
        if (!remove) {
            List<SmsConfigModel> smsConfigForFlush = smsTemplateRelationMapper.selectSmsConfigForFlush(Arrays.asList(templateIds));
            buildAndFlush(smsConfigForFlush);
        } else {
            removeConfig(templateIds);
        }
    }

    private void removeConfig(Long[] templateIds) {
        for (Long templateId : templateIds) {
            configMap.get(PushMethods.SMS).remove(templateId);
        }
    }

    @Data
    public static class SmsConfig implements Config {

        private long smsTemplateId;

        private long providerTemplateId;

        private int priority;

        private int templateStatus;

        private String code;

        private String sign;

        private String content;

        private List<String> params;

        private int platformTemplateStatus;

        private String providerName;

        private String scriptTag;

        private JSONObject config;

    }
}

