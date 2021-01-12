package com.zte.msg.pushcenter.pccore.service;

import com.zte.msg.pushcenter.pccore.core.pusher.msg.MailMessage;
import com.zte.msg.pushcenter.pccore.core.pusher.msg.SmsMessage;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/12 13:41
 */
public interface PushCenterService {

    void pushSms(SmsMessage smsMessage);

    void pushMail(MailMessage mailMessage);
}
