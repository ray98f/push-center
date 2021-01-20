package com.zte.msg.pushcenter.pccore.core.warn;

import com.zte.msg.pushcenter.pccore.core.pusher.msg.MailMessage;
import com.zte.msg.pushcenter.pccore.core.pusher.msg.SmsMessage;
import com.zte.msg.pushcenter.pccore.core.pusher.msg.WeChatMessage;
import com.zte.msg.pushcenter.pccore.dto.req.MailMessageReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.SmsMessageReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.WeChatMessageReqDTO;
import com.zte.msg.pushcenter.pccore.entity.EarlyWarnConfig;
import com.zte.msg.pushcenter.pccore.entity.User;
import com.zte.msg.pushcenter.pccore.model.EarlyWarnConfigModel;
import com.zte.msg.pushcenter.pccore.service.EarlyWarnService;
import com.zte.msg.pushcenter.pccore.service.PushCenterService;
import com.zte.msg.pushcenter.pccore.service.UserService;
import com.zte.msg.pushcenter.pccore.utils.Constants;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.Instant;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
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
        BeanUtils.copyProperties(earlyWarnConfig, warnConfig);
        warnConfig.setUsers(userService.listUser(userIds));
        statTime = Instant.now().getMillis();
        endTime = earlyWarnConfig.getAlarmCycle() + statTime;
        warnCount = 0;
    }

    private long lastWarnTime = 0;

    @Resource
    private PushCenterService pushCenterService;

    public void doWarn() {
        pushSms();
        pushMail();
        pushWechat();
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
        SmsMessageReqDTO reqDTO = new SmsMessageReqDTO();
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
        if (warnCount < warnConfig.getThreshold()) {
            warnCount++;
        } else if ((nowMillis - lastWarnTime) > warnConfig.getAlarmInterval()) {
            doWarn();
            lastWarnTime = nowMillis;
            warnCount = 0;
        }

    }

    public void submitWarn(Instant now) {
        if (Objects.isNull(warnConfig)) {
            log.warn("Cannot set up an invalid early-warn config ! Early-warn will be cancelled !");
        }
        warnMatch(now);
    }

    public void updateWarnConfig() {
        init();
    }

    @Data
    public static class TimeRecord {

        private Instant earliest;

        private Instant recent;
    }

}
