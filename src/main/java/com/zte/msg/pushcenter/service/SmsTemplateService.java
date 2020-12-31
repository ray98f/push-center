package com.zte.msg.pushcenter.service;

import com.zte.msg.pushcenter.core.pusher.SmsPusher;
import com.zte.msg.pushcenter.dto.req.SmsTemplateReqDTO;
import com.zte.msg.pushcenter.dto.res.SmsTemplateResDTO;

import java.util.List;

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

    /**
     * 根据templateId查询短信模版详情
     *
     * @param templateId
     * @return
     */
    SmsTemplateResDTO selectTemplate(String templateId);

    /**
     * 查询全量配置
     * @return
     */
    List<SmsPusher.ConfigDetail> selectConfigDetail();



}
