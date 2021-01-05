package com.zte.msg.pushcenter.pccore.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zte.msg.pushcenter.pccore.dto.PageReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.SmsTemplateReqDTO;
import com.zte.msg.pushcenter.pccore.dto.res.SmsTemplateDetailResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.SmsTemplateResDTO;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/21 10:45
 */
public interface TemplateService {


    /**
     * 添加短信模版
     *
     * @param smsTemplateReqDTO
     * @return
     */
    void addSmsTemplate(SmsTemplateReqDTO smsTemplateReqDTO);

    /**
     * 根据templateId查询短信模版详情
     *
     * @param templateId
     * @return
     */
    SmsTemplateResDTO getTemplate(String templateId);

    /**
     * 分页查询模版
     *
     * @return
     */
    Page<SmsTemplateDetailResDTO> getTemplateByPage(String example, PageReqDTO page);

}
