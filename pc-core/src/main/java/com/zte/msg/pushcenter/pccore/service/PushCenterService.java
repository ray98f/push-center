package com.zte.msg.pushcenter.pccore.service;

import com.zte.msg.pushcenter.pccore.core.pusher.SmsPusher;
import com.zte.msg.pushcenter.pccore.core.pusher.msg.AppMessage;
import com.zte.msg.pushcenter.pccore.core.pusher.msg.MailMessage;
import com.zte.msg.pushcenter.pccore.core.pusher.msg.SmsMessage;
import com.zte.msg.pushcenter.pccore.core.pusher.msg.WeChatMessage;
import com.zte.msg.pushcenter.pccore.dto.req.CidsReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.RegisterReqDTO;

import java.util.List;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/12 13:41
 */
public interface PushCenterService {

    /**
     * 短信推送
     *
     * @param smsMessage
     */
    void pushSms(SmsMessage smsMessage);

    /**
     * 获取对应模板id的短信配置
     *
     * @param templateId
     * @return
     */
    SmsPusher.SmsConfig getSmsConfig(Long templateId);

    /**
     * 推送邮件
     *
     * @param mailMessage
     */
    void pushMail(MailMessage mailMessage);

    /**
     * 推送微信消息
     *
     * @param weChatMessage
     */
    void pushWechat(WeChatMessage weChatMessage);

    /**
     * 推送app消息
     *
     * @param appMessage
     */
    void pushApp(AppMessage appMessage);

    void register(RegisterReqDTO reqDTO);

    String getCid(String sysCode, String userId);

    List<String> getCids(CidsReqDTO cidsReqDTO);
}
