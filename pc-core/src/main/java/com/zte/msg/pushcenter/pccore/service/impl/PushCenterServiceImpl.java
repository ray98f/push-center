package com.zte.msg.pushcenter.pccore.service.impl;

import com.zte.msg.pushcenter.pccore.core.pusher.MailPusher;
import com.zte.msg.pushcenter.pccore.core.pusher.SmsPusher;
import com.zte.msg.pushcenter.pccore.core.pusher.msg.MailMessage;
import com.zte.msg.pushcenter.pccore.core.pusher.msg.SmsMessage;
import com.zte.msg.pushcenter.pccore.dto.res.ProviderResDTO;
import com.zte.msg.pushcenter.pccore.enums.ErrorCode;
import com.zte.msg.pushcenter.pccore.enums.PushMethods;
import com.zte.msg.pushcenter.pccore.exception.CommonException;
import com.zte.msg.pushcenter.pccore.service.ProviderService;
import com.zte.msg.pushcenter.pccore.service.PushCenterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/12 13:42
 */
@Service
@Slf4j
public class PushCenterServiceImpl implements PushCenterService {

    @Resource
    private SmsPusher smsPusher;

    @Resource
    private MailPusher mailPusher;

    @Resource
    private ProviderService providerService;

    @Override
    public void pushSms(SmsMessage smsMessage) {
        smsPusher.submit(smsMessage);
    }

    @Override
    public void pushMail(MailMessage mailMessage) {
        ProviderResDTO providerById = providerService.getProviderById(mailMessage.getProviderId());
        if (PushMethods.valueOf(providerById.getType()) != PushMethods.MAIL) {
            throw new CommonException(ErrorCode.PROVIDER_TYPE_NOT_CORRECT);
        }
        mailPusher.submit(mailMessage);
    }
}
