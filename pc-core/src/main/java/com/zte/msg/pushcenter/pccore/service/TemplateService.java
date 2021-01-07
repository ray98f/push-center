package com.zte.msg.pushcenter.pccore.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zte.msg.pushcenter.pccore.dto.PageReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.SmsTemplateRelateProviderReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.SmsTemplateRelateProviderUpdateReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.SmsTemplateReqDTO;
import com.zte.msg.pushcenter.pccore.dto.res.ProviderSmsTemplateResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.SmsTemplateDetailResDTO;

import java.util.List;

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

    void updateSmsTemplate(Long templateId, SmsTemplateReqDTO smsTemplateReqDTO);

    void deleteSmsTemplate(Long[] templateIds);

    /**
     * 为平台短信模版添加供应商短信模版关联
     *
     * @param templateId
     * @param reqDTO
     */
    void addProviderSmsTemplateRelate(Long templateId,
                                      SmsTemplateRelateProviderReqDTO reqDTO);

    void updateProviderSmsTemplateRelate(Long templateId,
                                         SmsTemplateRelateProviderUpdateReqDTO reqDTO);

    void deleteProviderSmsTemplateRelate(Long templateId,
                                         Long[] ids);

    List<ProviderSmsTemplateResDTO> getProviderSmsTemplatesByTemplateId(Long templateId);


    /**
     * 分页查询模版
     *
     * @return
     */
    Page<SmsTemplateDetailResDTO> getTemplateByPage(String content,
                                                    Long templateId,
                                                    Integer status,
                                                    PageReqDTO pageReqDTO);

}
