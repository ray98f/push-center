package com.zte.msg.pushcenter.pccore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zte.msg.pushcenter.pccore.dto.req.ProviderSmsTemplateReqDTO;
import com.zte.msg.pushcenter.pccore.dto.res.ProviderSmsTemplateResDTO;
import com.zte.msg.pushcenter.pccore.entity.ProviderSmsTemplate;
import com.zte.msg.pushcenter.pccore.enums.ErrorCode;
import com.zte.msg.pushcenter.pccore.exception.CommonException;
import com.zte.msg.pushcenter.pccore.mapper.PlatformSmsTemplateMapper;
import com.zte.msg.pushcenter.pccore.service.ProviderService;
import com.zte.msg.pushcenter.pccore.service.ProviderSmsTemplateService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/7 15:56
 */
@Service
public class ProviderSmsTemplateServiceImpl extends ServiceImpl<PlatformSmsTemplateMapper, ProviderSmsTemplate>
        implements ProviderSmsTemplateService {

    @Resource
    private ProviderService providerService;

    @Override
    public void addSmsProviderTemplate(Long providerId, ProviderSmsTemplateReqDTO smsTemplateReqDTO) {
        ProviderSmsTemplate providerSmsTemplate = new ProviderSmsTemplate();
        providerSmsTemplate.setCode(smsTemplateReqDTO.getCode());
        providerSmsTemplate.setContent(smsTemplateReqDTO.getContent());
        providerSmsTemplate.setProviderId(providerId);
        providerSmsTemplate.setSign(smsTemplateReqDTO.getSign());
        providerSmsTemplate.setStatus(smsTemplateReqDTO.getStatus());
        getBaseMapper().insert(providerSmsTemplate);
    }

    @Override
    public void updateSmsProviderTemplate(Long providerId,
                                          Long providerSmsTemplateId,
                                          ProviderSmsTemplateReqDTO smsTemplateReqDTO) {

        ProviderSmsTemplate providerSmsTemplate = new ProviderSmsTemplate();
        BeanUtils.copyProperties(smsTemplateReqDTO, providerSmsTemplate);
        providerSmsTemplate.setId(providerSmsTemplateId);
        providerSmsTemplate.setProviderId(providerId);
        getBaseMapper().updateById(providerSmsTemplate);
    }

    @Override
    public void deleteSmsProviderTemplate(Long providerId, Long providerSmsTemplateId) {

        if (Objects.isNull(providerService.getProviderById(providerId))) {
            throw new CommonException(ErrorCode.PROVIDER_NOT_EXIST);
        }
        if (Objects.isNull(getBaseMapper().selectById(providerSmsTemplateId))) {
            throw new CommonException(ErrorCode.SMS_TEMPLATE_NOT_EXIST);
        }
        getBaseMapper().deleteById(providerSmsTemplateId);
    }

    @Override
    public List<ProviderSmsTemplateResDTO> getProviderSmsTemplatesByProviderId(Long providerId) {
        QueryWrapper<ProviderSmsTemplate> wrapper = new QueryWrapper<>();
        if (Objects.nonNull(providerId)) {
            wrapper.eq("provider_id", providerId);
        }
        List<ProviderSmsTemplate> providerSmsTemplates = getBaseMapper().selectList(wrapper);

        List<ProviderSmsTemplateResDTO> list = new ArrayList<>(providerSmsTemplates.size());
        providerSmsTemplates.forEach(o -> {
            ProviderSmsTemplateResDTO providerSmsTemplateResDTO = new ProviderSmsTemplateResDTO();
            BeanUtils.copyProperties(o, providerSmsTemplateResDTO);
            providerSmsTemplateResDTO.setId(o.getId());
            list.add(providerSmsTemplateResDTO);
        });

        return list;
    }
}
