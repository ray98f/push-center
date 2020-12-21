package com.zte.msg.pushcenter.service;

import com.zte.msg.pushcenter.dto.req.ProviderReqDTO;
import com.zte.msg.pushcenter.dto.req.SmsTemplateReqDTO;
import com.zte.msg.pushcenter.dto.res.ProviderResDTO;
import com.zte.msg.pushcenter.dto.res.SmsTemplateResDTO;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/21 10:45
 */
public interface SmsTemplateService {



    /**
     * 添加短信模版
     *
     * @param smsTemplateReqDTO
     * @return
     */
    SmsTemplateResDTO addSmsTemplate(SmsTemplateReqDTO smsTemplateReqDTO);

}
