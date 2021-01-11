package com.zte.msg.pushcenter.pccore.service;

import com.zte.msg.pushcenter.pccore.dto.req.ProviderSmsTemplateReqDTO;
import com.zte.msg.pushcenter.pccore.dto.res.ProviderSmsTemplateResDTO;

import java.util.List;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/7 15:56
 */
public interface ProviderSmsTemplateService {

    /**
     * 新增第三方消息平台的短信模版
     *
     * @param providerId
     * @param smsTemplateReqDTO
     */
    void addSmsProviderTemplate(Long providerId,
                                ProviderSmsTemplateReqDTO smsTemplateReqDTO);

    void updateSmsProviderTemplate(Long providerId,
                                   Long providerSmsTemplateId,
                                   ProviderSmsTemplateReqDTO smsTemplateReqDTO);

    void deleteSmsProviderTemplate(Long providerId,
                                   Long providerSmsTemplateId);

    List<ProviderSmsTemplateResDTO> getProviderSmsTemplatesByProviderId(Long providerId);
}
