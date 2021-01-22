package com.zte.msg.pushcenter.pccore.core.warn;

import com.zte.msg.pushcenter.pccore.core.pusher.SmsPusher;
import com.zte.msg.pushcenter.pccore.core.pusher.msg.MailMessage;
import com.zte.msg.pushcenter.pccore.core.pusher.msg.SmsMessage;
import com.zte.msg.pushcenter.pccore.core.pusher.msg.WeChatMessage;
import com.zte.msg.pushcenter.pccore.dto.req.MailMessageReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.SmsMessageReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.WeChatMessageReqDTO;
import com.zte.msg.pushcenter.pccore.entity.EarlyWarnConfig;
import com.zte.msg.pushcenter.pccore.entity.EarlyWarnInfo;
import com.zte.msg.pushcenter.pccore.entity.User;
import com.zte.msg.pushcenter.pccore.model.EarlyWarnConfigModel;
import com.zte.msg.pushcenter.pccore.service.EarlyWarnInfoService;
import com.zte.msg.pushcenter.pccore.service.EarlyWarnService;
import com.zte.msg.pushcenter.pccore.service.PushCenterService;
import com.zte.msg.pushcenter.pccore.service.UserService;
import com.zte.msg.pushcenter.pccore.utils.Constants;
import com.zte.msg.pushcenter.pccore.utils.DateUtils;
import com.zte.msg.pushcenter.pccore.utils.PatternUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.Instant;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/20 10:37
 */
@Service
@Slf4j
public class WarnHandler {

    @Resource
    private EarlyWarnService earlyWarnService;

    @Resource
    private EarlyWarnInfoService earlyWarnInfoService;

    @Resource
    private UserService userService;

    private EarlyWarnConfigModel warnConfig = null;

    private long statTime;

    private long endTime;

    private int warnCount;

    @PostConstruct
    public void init() {
        EarlyWarnConfig earlyWarnConfig = earlyWarnService.selectEarlyWarnConfig();
        List<Long> userIds = Arrays.stream(earlyWarnConfig.getUserIds()
                .split(Constants.COMMA_EN)).map(Long::parseLong).collect(Collectors.toList());
        warnConfig = new EarlyWarnConfigModel();
        BeanUtils.copyProperties(earlyWarnConfig, warnConfig);
        warnConfig.setUsers(userService.listUser(userIds));
        warnConfig.setAlarmCycle(earlyWarnConfig.getAlarmCycle() * Constants.PER_MINUTE_MILLS);
        warnConfig.setAlarmInterval(earlyWarnConfig.getAlarmInterval() * Constants.PER_MINUTE_MILLS);
        statTime = Instant.now().getMillis();
        endTime = earlyWarnConfig.getAlarmCycle() + statTime;
        warnCount = 0;
    }

    private long lastWarnTime = 0;

    @Resource
    private PushCenterService pushCenterService;

    public void doWarn(Instant now) {

        pushSms();
        pushMail();
        pushWechat();
        persist(now);
    }

    private void pushWechat() {
        String[] openIds = warnConfig.getOpenIds().split(Constants.COMMA_EN);
        if (StringUtils.isBlank(warnConfig.getOpenIds()) || Objects.isNull(warnConfig.getWechatProviderId())) {
            return;
        }
        for (String openId : openIds) {
            WeChatMessageReqDTO reqDTO = new WeChatMessageReqDTO();
            reqDTO.setOpenId(openId);
            reqDTO.setProviderId(warnConfig.getWechatProviderId());
            reqDTO.setTemplateId(warnConfig.getWechatTemplateId());
            reqDTO.setData(warnConfig.getWechatData());
            reqDTO.setMessageId(UUID.randomUUID().toString());
            reqDTO.setAppId(warnConfig.getAppId());
            reqDTO.setIsCallBack(false);

            WeChatMessage weChatMessage = new WeChatMessage().build(reqDTO);
            pushCenterService.pushWechat(weChatMessage);
        }
    }

    private void pushMail() {
        if (StringUtils.isAllBlank(warnConfig.getMailBody(), warnConfig.getMailTitle())) {
            return;
        }
        MailMessageReqDTO reqDTO = new MailMessageReqDTO();
        reqDTO.setTo(warnConfig.getUsers().stream().map(User::getMail).toArray(String[]::new));
        reqDTO.setProviderId(warnConfig.getMailProviderId());
        reqDTO.setContent(warnConfig.getMailBody());
        reqDTO.setMessageId(UUID.randomUUID().toString());
        reqDTO.setAppId(warnConfig.getAppId());
        reqDTO.setIsCallBack(false);

        MailMessage message = new MailMessage().build(reqDTO);
        pushCenterService.pushMail(message);
    }

    private void pushSms() {
        String[] phoneNums = warnConfig.getUsers().stream().map(User::getPhone).toArray(String[]::new);
        Map<String, String> var = new HashMap<>(8);
        SmsPusher.SmsConfig smsConfig = pushCenterService.getSmsConfig(warnConfig.getSmsTemplateId());
        List<String> params = smsConfig.getParams();
        if (params.size() < 3) {
            log.error("预警配置模板参数数量与规定不符");
        }

        var.put(params.get(0), DateUtils.formatDate(new Date(statTime)));
        var.put(params.get(1), DateUtils.formatDate(new Date(endTime)));
        var.put(params.get(2), warnCount + "");

        SmsMessageReqDTO reqDTO = new SmsMessageReqDTO();
        reqDTO.setVars(var);
        reqDTO.setPhoneNum(phoneNums);
        reqDTO.setTemplateId(warnConfig.getSmsTemplateId());
        reqDTO.setMessageId(UUID.randomUUID().toString());
        reqDTO.setAppId(warnConfig.getAppId());
        reqDTO.setIsCallBack(false);
        SmsMessage smsMessage = new SmsMessage().build(reqDTO);
        pushCenterService.pushSms(smsMessage);
    }

    public void warnMatch(Instant now) {
        long nowMillis = now.getMillis();
        while (nowMillis > endTime) {
            statTime = endTime;
            endTime += warnConfig.getAlarmCycle();
        }
        warnCount++;
        if (warnCount >= warnConfig.getThreshold() &&
                (nowMillis - lastWarnTime) > warnConfig.getAlarmInterval()) {
            doWarn(now);
            lastWarnTime = nowMillis;
            warnCount = 0;
        }

    }

    public void submitWarn(Instant now) {
        if (Objects.isNull(warnConfig)) {
            log.warn("Cannot set up an invalid early-warn config ! Early-warn will be cancelled !");
        }
        log.info("Receive a push fail warn ....");
        warnMatch(now);
    }

    public void updateWarnConfig() {
        init();
    }

    public void persist(Instant now) {
        EarlyWarnInfo earlyWarnInfo = new EarlyWarnInfo();
        earlyWarnInfo.setTime(new Timestamp(now.getMillis()));
        earlyWarnInfo.setReason(String.format("消息平台%s分钟内，推送失败次数超过%s次", warnConfig.getAlarmCycle() / 1000 / 60,
                warnConfig.getThreshold()));
        earlyWarnInfo.setContent(PatternUtils.buildContent(pushCenterService.getSmsConfig(warnConfig.getSmsTemplateId()).getContent(),
                new Date(statTime), new Date(endTime), warnConfig.getThreshold()));
        earlyWarnInfo.setDisposer(StringUtils.join(warnConfig.getUsers().stream().map(User::getUserName).toArray(), Constants.COMMA_EN));
        earlyWarnInfoService.save(earlyWarnInfo);
    }
}
