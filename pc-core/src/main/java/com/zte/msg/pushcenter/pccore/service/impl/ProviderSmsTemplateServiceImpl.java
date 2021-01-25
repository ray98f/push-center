package com.zte.msg.pushcenter.pccore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zte.msg.pushcenter.pccore.dto.req.ProviderSmsTemplateReqDTO;
import com.zte.msg.pushcenter.pccore.dto.res.ProviderSmsTemplateResDTO;
import com.zte.msg.pushcenter.pccore.entity.ProviderSmsTemplate;
import com.zte.msg.pushcenter.pccore.entity.SmsTemplateRelation;
import com.zte.msg.pushcenter.pccore.enums.ErrorCode;
import com.zte.msg.pushcenter.pccore.exception.CommonException;
import com.zte.msg.pushcenter.pccore.mapper.ProviderSmsTemplateMapper;
import com.zte.msg.pushcenter.pccore.mapper.SmsTemplateRelationMapper;
import com.zte.msg.pushcenter.pccore.service.ProviderService;
import com.zte.msg.pushcenter.pccore.service.ProviderSmsTemplateService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
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
public class ProviderSmsTemplateServiceImpl extends ServiceImpl<ProviderSmsTemplateMapper, ProviderSmsTemplate>
        implements ProviderSmsTemplateService {

    @Resource
    private ProviderService providerService;

    @Resource
    private SmsTemplateRelationMapper smsTemplateRelationMapper;

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
    @Transactional(rollbackFor = Exception.class)
    public void deleteSmsProviderTemplate(Long providerId, Long[] providerSmsTemplateIds) {

        List<Long> pstIds = Arrays.asList(providerSmsTemplateIds);
        if (Objects.isNull(providerService.getProviderById(providerId))) {
            throw new CommonException(ErrorCode.PROVIDER_NOT_EXIST);
        }
        getBaseMapper().deleteBatchIds(pstIds);
        // 删除该消息平台模版和消息中心模版关联关系
        pstIds.forEach(o -> smsTemplateRelationMapper.delete(new LambdaQueryWrapper<SmsTemplateRelation>()
                .eq(SmsTemplateRelation::getProviderTemplateId, o)));
    }

    @Override
    public List<ProviderSmsTemplateResDTO> getProviderSmsTemplatesByProviderId(Long providerId) {
        LambdaQueryWrapper<ProviderSmsTemplate> wrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(providerId)) {
            wrapper.eq(ProviderSmsTemplate::getProviderId, providerId);
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
