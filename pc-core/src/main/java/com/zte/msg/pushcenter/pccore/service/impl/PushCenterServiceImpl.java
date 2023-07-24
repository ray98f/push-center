package com.zte.msg.pushcenter.pccore.service.impl;

import com.baomidou.mybatisplus.extension.api.R;
import com.zte.msg.pushcenter.pccore.core.pusher.AppPusher;
import com.zte.msg.pushcenter.pccore.core.pusher.MailPusher;
import com.zte.msg.pushcenter.pccore.core.pusher.SmsPusher;
import com.zte.msg.pushcenter.pccore.core.pusher.WeChatPusher;
import com.zte.msg.pushcenter.pccore.core.pusher.base.Message;
import com.zte.msg.pushcenter.pccore.core.pusher.msg.AppMessage;
import com.zte.msg.pushcenter.pccore.core.pusher.msg.MailMessage;
import com.zte.msg.pushcenter.pccore.core.pusher.msg.SmsMessage;
import com.zte.msg.pushcenter.pccore.core.pusher.msg.WeChatMessage;
import com.zte.msg.pushcenter.pccore.dto.req.CidsReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.RegisterReqDTO;
import com.zte.msg.pushcenter.pccore.dto.res.ProviderResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.RegisterResDTO;
import com.zte.msg.pushcenter.pccore.enums.ErrorCode;
import com.zte.msg.pushcenter.pccore.enums.PushMethods;
import com.zte.msg.pushcenter.pccore.exception.CommonException;
import com.zte.msg.pushcenter.pccore.mapper.RegisterMapper;
import com.zte.msg.pushcenter.pccore.service.ProviderService;
import com.zte.msg.pushcenter.pccore.service.PushCenterService;
import com.zte.msg.pushcenter.pccore.utils.TokenUtil;
import jdk.nashorn.internal.parser.Token;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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
    private WeChatPusher weChatPusher;

    @Resource
    private AppPusher appPusher;

    @Resource
    private ProviderService providerService;

    @Resource
    private RegisterMapper registerMapper;

    @Override
    public void pushSms(SmsMessage smsMessage) {

        smsPusher.submit(smsMessage);
    }

    @Override
    public SmsPusher.SmsConfig getSmsConfig(Long templateId) {
        return smsPusher.getConfig(templateId);
    }

    @Override
    public void pushMail(MailMessage mailMessage) {
        checkProviderType(mailMessage, mailMessage.getProviderId());
        mailPusher.submit(mailMessage);
    }

    @Override
    public void pushWechat(WeChatMessage weChatMessage) {
        checkProviderType(weChatMessage, weChatMessage.getProviderId());
        weChatPusher.submit(weChatMessage);
    }

    @Override
    public void pushApp(AppMessage appMessage) {
        checkProviderType(appMessage, appMessage.getProviderId());
        appPusher.submit(appMessage);
    }

    @Override
    public void register(RegisterReqDTO reqDTO) {
        reqDTO.setUpdateBy(reqDTO.getUserId());
        reqDTO.setCreateBy(reqDTO.getUserId());
        reqDTO.setUserId(reqDTO.getUserId());

        if (StringUtils.isBlank(reqDTO.getSysCode()) || StringUtils.isBlank(reqDTO.getUserId())) {
            throw new CommonException(ErrorCode.PARAM_ERROR);
        }
        RegisterResDTO res = registerMapper.selectRegisterInfo(reqDTO);
        if (Objects.isNull(res)) {
            registerMapper.register(reqDTO);
        } else {
            registerMapper.modifyRegisterInfo(reqDTO);
        }
    }

    @Override
    public String getCid(String sysCode, String userId) {
        if (StringUtils.isBlank(sysCode) || StringUtils.isBlank(userId)) {
            throw new CommonException(ErrorCode.PARAM_ERROR);
        }
        RegisterReqDTO dto = new RegisterReqDTO();
        dto.setSysCode(sysCode);
        dto.setUserId(userId);
        RegisterResDTO res = registerMapper.selectRegisterInfo(dto);
        return res.getCid();
    }

    @Override
    public List<String> getCids(CidsReqDTO cidsReqDTO) {
        if (Objects.isNull(cidsReqDTO) || StringUtils.isBlank(cidsReqDTO.getSysCode())
                || Objects.isNull(cidsReqDTO.getUserIds()) || cidsReqDTO.getUserIds().size() <= 0) {
            throw new CommonException(ErrorCode.PARAM_ERROR);
        }
        RegisterReqDTO dto = new RegisterReqDTO();
        dto.setSysCode(cidsReqDTO.getSysCode());
        dto.setUserIds(cidsReqDTO.getUserIds());

        List<String> res = registerMapper.selectRegisterInfos(dto);
        Set<String> ss = new HashSet<>(res);
        return new ArrayList<>(ss);
    }

    private void checkProviderType(Message message, Long providerId) {
        ProviderResDTO providerById = providerService.getProviderById(providerId);
        if (PushMethods.valueOf(providerById.getType()) != message.getPushMethod()) {
            throw new CommonException(ErrorCode.PROVIDER_TYPE_NOT_CORRECT);
        }
    }

}
