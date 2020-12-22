package com.zte.msg.pushcenter.service;

import com.zte.msg.pushcenter.dto.req.SmsProviderConfigReqDTO;
import com.zte.msg.pushcenter.dto.res.SmsProviderConfigResDTO;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/22 14:52
 */
public interface SmsProviderConfigService {

    SmsProviderConfigResDTO addSmsProviderConfig(SmsProviderConfigReqDTO reqDTO);
}
