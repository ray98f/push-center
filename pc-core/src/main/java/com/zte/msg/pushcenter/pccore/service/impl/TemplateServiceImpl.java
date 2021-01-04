package com.zte.msg.pushcenter.pccore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zte.msg.pushcenter.pccore.dto.req.ProviderSmsTemplateReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.SmsTemplateReqDTO;
import com.zte.msg.pushcenter.pccore.dto.res.SmsTemplateResDTO;
import com.zte.msg.pushcenter.pccore.entity.ProviderSmsTemplate;
import com.zte.msg.pushcenter.pccore.entity.Template;
import com.zte.msg.pushcenter.pccore.enums.ErrorCode;
import com.zte.msg.pushcenter.pccore.enums.PushMethods;
import com.zte.msg.pushcenter.pccore.exception.CommonException;
import com.zte.msg.pushcenter.pccore.mapper.TemplateMapper;
import com.zte.msg.pushcenter.pccore.service.ProviderSmsTemplateService;
import com.zte.msg.pushcenter.pccore.service.SmsConfigService;
import com.zte.msg.pushcenter.pccore.service.TemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/21 10:48
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class TemplateServiceImpl extends ServiceImpl<TemplateMapper, Template> implements TemplateService {

    @Resource
    private SmsConfigService smsConfigService;

    @Resource
    private ProviderSmsTemplateService providerSmsTemplateService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addSmsTemplate(SmsTemplateReqDTO smsTemplateReqDTO) {

        List<ProviderSmsTemplateReqDTO> providerSmsTemplates = smsTemplateReqDTO.getProviderSmsTemplates();
        if (providerSmsTemplates.isEmpty()) {
            throw new CommonException(ErrorCode.SMS_PROVIDER_CONFIG_NOT_NULL);
        }
        providerSmsTemplates.forEach(o -> {
            if (Objects.isNull(smsConfigService.getById(o.getSmsConfigId()))) {
                throw new CommonException(ErrorCode.SMS_CONFIG_NOT_EXIST);
            }
        });
        Template entity = new Template();
        BeanUtils.copyProperties(smsTemplateReqDTO, entity);
        entity.setType(PushMethods.SMS.value());
        getBaseMapper().insert(entity);
        Long id = entity.getId();

        List<ProviderSmsTemplate> smsTemplates = new ArrayList<>(providerSmsTemplates.size());
        providerSmsTemplates.forEach(o -> {
            ProviderSmsTemplate pst = new ProviderSmsTemplate();
            BeanUtils.copyProperties(o, pst);
            pst.setSmsTemplateId(id);
            smsTemplates.add(pst);
        });
        providerSmsTemplateService.saveBatch(smsTemplates);
    }

    @Override
    public SmsTemplateResDTO selectTemplate(String templateId) {

        return null;
    }



}
