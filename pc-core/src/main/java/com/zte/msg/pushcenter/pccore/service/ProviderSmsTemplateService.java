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

    /**
     * 更新消息平台短信模板
     *
     * @param providerId
     * @param providerSmsTemplateId
     * @param smsTemplateReqDTO
     */
    void updateSmsProviderTemplate(Long providerId,
                                   Long providerSmsTemplateId,
                                   ProviderSmsTemplateReqDTO smsTemplateReqDTO);

    /**
     * 删除消息平台短信模板
     *
     * @param providerId
     * @param providerSmsTemplateIds
     */
    void deleteSmsProviderTemplate(Long providerId,
                                   Long[] providerSmsTemplateIds);

    /**
     * 更具providerId查询该消息平台下所有的短信模板
     *
     * @param providerId
     * @return
     */
    List<ProviderSmsTemplateResDTO> getProviderSmsTemplatesByProviderId(Long providerId);

}
