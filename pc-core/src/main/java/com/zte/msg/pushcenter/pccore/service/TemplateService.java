package com.zte.msg.pushcenter.pccore.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zte.msg.pushcenter.pccore.dto.PageReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.*;
import com.zte.msg.pushcenter.pccore.dto.res.ProviderSmsTemplateResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.SmsTemplateDetailResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.WeChatTemplateResDTO;
import com.zte.msg.pushcenter.pccore.entity.SmsTemplate;

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

    /**
     * 更新短信模板
     *
     * @param templateId
     * @param smsTemplateReqDTO
     */
    void updateSmsTemplate(Long templateId, SmsTemplateReqDTO smsTemplateReqDTO);

    /**
     * 删除短信模板
     *
     * @param templateIds
     */
    void deleteSmsTemplate(Long[] templateIds);

    /**
     * 为平台短信模版添加供应商短信模版关联
     *
     * @param templateId
     * @param reqDTO
     */
    void addProviderSmsTemplateRelate(Long templateId,
                                      SmsTemplateRelateProviderReqDTO reqDTO);

    /**
     * 更新模板关联关系
     *
     * @param templateId
     * @param reqDTO
     */
    void updateProviderSmsTemplateRelate(Long templateId,
                                         SmsTemplateRelateProviderUpdateReqDTO reqDTO);

    /**
     * 删除短信模板下关联的第三方模板
     *
     * @param templateId
     * @param ids
     */
    void deleteProviderSmsTemplateRelate(Long templateId,
                                         Long[] ids);

    /**
     * 根据模板id查询第三方模板详情
     *
     * @param templateId
     * @return
     */
    List<ProviderSmsTemplateResDTO> getProviderSmsTemplatesByTemplateId(Long templateId);


    /**
     * 分页查询模版
     *
     * @param content
     * @param templateId
     * @param status
     * @param pageReqDTO
     * @return
     */
    Page<SmsTemplateDetailResDTO> getTemplateByPage(String content,
                                                    Long templateId,
                                                    Integer status,
                                                    PageReqDTO pageReqDTO);

    /**
     * 全量查询模版列表
     *
     * @return
     */
    List<SmsTemplate> getTemplateList();

    /**
     * 添加微信模板
     *
     * @param reqDTO
     */
    void addWeChatTemplate(WeChatTemplateReqDTO reqDTO);

    /**
     * 更新微信模板
     *
     * @param reqDTO
     */
    void updateWeChatTemplate(WeChatTemplateUpdateReqDTO reqDTO);

    /**
     * 根据id查询微信模板
     *
     * @param templateId
     * @return
     */
    WeChatTemplateResDTO getWeChatTemplate(Long templateId);

    /**
     * 删除微信模板
     *
     * @param ids
     */
    void deleteWeChatTemplates(Long[] ids);

    /**
     * 分页查询微信模板
     *
     * @param page
     * @param templateId
     * @param providerName
     * @param status
     * @return
     */
    Page<WeChatTemplateResDTO> getWeChatTemplates(PageReqDTO page,
                                                  Long templateId,
                                                  String providerName,
                                                  Integer status);
}
