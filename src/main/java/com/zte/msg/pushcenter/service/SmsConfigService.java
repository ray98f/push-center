package com.zte.msg.pushcenter.service;

import com.zte.msg.pushcenter.dto.req.SmsConfigReqDTO;
import com.zte.msg.pushcenter.dto.res.SmsConfigResDTO;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/22 14:52
 */
public interface SmsConfigService {

    SmsConfigResDTO addSmsConfig(SmsConfigReqDTO reqDTO);

    void updateSmsConfig(Long smsConfigId, SmsConfigReqDTO smsConfig);

    void deleteSmsConfig(Long deleteSmsConfig);
}
