package com.zte.msg.pushcenter.pccore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zte.msg.pushcenter.pccore.dto.PageReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.SmsTemplateRelateProviderReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.SmsTemplateRelateProviderUpdateReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.SmsTemplateReqDTO;
import com.zte.msg.pushcenter.pccore.dto.res.ProviderSmsTemplateResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.SmsTemplateDetailResDTO;
import com.zte.msg.pushcenter.pccore.entity.ProviderSmsTemplate;
import com.zte.msg.pushcenter.pccore.entity.SmsTemplate;
import com.zte.msg.pushcenter.pccore.entity.SmsTemplateRelation;
import com.zte.msg.pushcenter.pccore.enums.ErrorCode;
import com.zte.msg.pushcenter.pccore.exception.CommonException;
import com.zte.msg.pushcenter.pccore.mapper.PlatformSmsTemplateMapper;
import com.zte.msg.pushcenter.pccore.mapper.SmsTemplateMapper;
import com.zte.msg.pushcenter.pccore.mapper.SmsTemplateRelationMapper;
import com.zte.msg.pushcenter.pccore.model.SmsTemplateRelationModel;
import com.zte.msg.pushcenter.pccore.service.TemplateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

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
public class TemplateServiceImpl implements TemplateService {

    @Resource
    private SmsTemplateMapper smsTemplateMapper;

    @Resource
    private PlatformSmsTemplateMapper providerSmsTemplateRelateMapper;

    @Resource
    private SmsTemplateRelationMapper smsTemplateRelationMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addSmsTemplate(SmsTemplateReqDTO smsTemplateReqDTO) {
        String params = StringUtils.join(smsTemplateReqDTO.getParams(), ",");
        SmsTemplate smsTemplate = new SmsTemplate();
        BeanUtils.copyProperties(smsTemplateReqDTO, smsTemplate);
        smsTemplate.setParams(params);
        smsTemplateMapper.insert(smsTemplate);
    }

    @Override
    public void updateSmsTemplate(Long templateId, SmsTemplateReqDTO smsTemplateReqDTO) {
        SmsTemplate smsTemplate = new SmsTemplate();
        BeanUtils.copyProperties(smsTemplateReqDTO, smsTemplate);
        smsTemplate.setId(templateId);
        smsTemplate.setParams(StringUtils.join(smsTemplateReqDTO.getParams(), ","));
        smsTemplateMapper.updateById(smsTemplate);
    }

    @Override
    public void deleteSmsTemplate(Long[] templateIds) {
        smsTemplateMapper.deleteBatchIds(Arrays.asList(templateIds));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addProviderSmsTemplateRelate(Long templateId, SmsTemplateRelateProviderReqDTO reqDTO) {
        if (Objects.isNull(smsTemplateMapper.selectById(templateId))) {
            throw new CommonException(ErrorCode.SMS_TEMPLATE_NOT_EXIST);
        }
        if (Objects.nonNull(smsTemplateRelationMapper.selectOne(new QueryWrapper<SmsTemplateRelation>()
                .eq("sms_template_id", templateId)
                .eq("provider_template_id", reqDTO.getPTemplateId())))) {

            throw new CommonException(ErrorCode.SMS_TEMPLATE_RELATION_ALREADY_EXIST);
        }
        SmsTemplateRelation relation = new SmsTemplateRelation();
        relation.setPriority(reqDTO.getPriority());
        relation.setProviderTemplateId(reqDTO.getPTemplateId());
        relation.setSmsTemplateId(templateId);
        smsTemplateRelationMapper.insert(relation);
    }

    @Override
    public void updateProviderSmsTemplateRelate(Long templateId, SmsTemplateRelateProviderUpdateReqDTO reqDTO) {
        if (Objects.isNull(smsTemplateMapper.selectById(templateId))) {
            throw new CommonException(ErrorCode.SMS_TEMPLATE_NOT_EXIST);
        }
        SmsTemplateRelation relation = smsTemplateRelationMapper.selectById(reqDTO.getRelationId());
        if (Objects.isNull(relation)) {
            throw new CommonException(ErrorCode.SMS_TEMPLATE_NOT_EXIST);
        }
        ProviderSmsTemplate providerSmsTemplate = providerSmsTemplateRelateMapper.selectById(relation.getProviderTemplateId());
        providerSmsTemplate.setStatus(reqDTO.getStatus());
        providerSmsTemplateRelateMapper.updateById(providerSmsTemplate);
        relation.setId(reqDTO.getRelationId());
        relation.setPriority(reqDTO.getPriority());
        relation.setSmsTemplateId(templateId);
        smsTemplateRelationMapper.updateById(relation);
    }

    @Override
    public void deleteProviderSmsTemplateRelate(Long templateId, Long[] ids) {
        if (Objects.isNull(smsTemplateMapper.selectById(templateId))) {
            throw new CommonException(ErrorCode.SMS_TEMPLATE_NOT_EXIST);
        }
        smsTemplateRelationMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public List<ProviderSmsTemplateResDTO> getProviderSmsTemplatesByTemplateId(Long templateId) {

        List<SmsTemplateRelationModel> smsTemplateRelationModels =
                smsTemplateRelationMapper.selectByTemplateId(templateId);
        List<ProviderSmsTemplateResDTO> resDTOList = new ArrayList<>(smsTemplateRelationModels.size());
        if (smsTemplateRelationModels.size() == 0) {
            return resDTOList;
        }
        smsTemplateRelationModels.forEach(o -> {
            ProviderSmsTemplateResDTO providerSmsTemplateResDTO = new ProviderSmsTemplateResDTO();
            providerSmsTemplateResDTO.setStatus(o.getPStatus());
            providerSmsTemplateResDTO.setPriority(o.getPriority());
            providerSmsTemplateResDTO.setCode(o.getCode());
            providerSmsTemplateResDTO.setContent(o.getContent());
            providerSmsTemplateResDTO.setSign(o.getSign());
            providerSmsTemplateResDTO.setRelationId(o.getRelationId());
            providerSmsTemplateResDTO.setId(o.getPTemplateId());
            providerSmsTemplateResDTO.setUpdatedAt(o.getUpdatedAt());
            providerSmsTemplateResDTO.setUpdatedBy(o.getUpdatedBy());
            resDTOList.add(providerSmsTemplateResDTO);
        });
        return resDTOList;
    }

    @Override
    public Page<SmsTemplateDetailResDTO> getTemplateByPage(String content,
                                                           Long templateId,
                                                           Integer status,
                                                           PageReqDTO pageReqDTO) {
        QueryWrapper<SmsTemplate> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(content)) {
            wrapper.like("content", content);
        }
        if (null != templateId) {
            wrapper.eq("id", templateId);
        }
        if (null != status) {
            wrapper.eq("status", status);
        }

        Page<SmsTemplate> smsTemplatePage = smsTemplateMapper.selectPage(pageReqDTO.of(), wrapper);
        List<SmsTemplate> templates = smsTemplatePage.getRecords();

        Page<SmsTemplateDetailResDTO> pageRes = new Page<>();
        BeanUtils.copyProperties(smsTemplatePage, pageRes);
        List<Long> templateIds = new ArrayList<>();
        Map<Long, SmsTemplateDetailResDTO> smsTemplateMap = new HashMap<>();
        templates.forEach(o -> {
            SmsTemplateDetailResDTO smsTemplateDetailResDTO = new SmsTemplateDetailResDTO();
            templateIds.add(o.getId());
            smsTemplateDetailResDTO.setId(o.getId());
            smsTemplateDetailResDTO.setContent(o.getContent());
            smsTemplateDetailResDTO.setParams(o.getParams());
            smsTemplateDetailResDTO.setStatus(o.getStatus());
            smsTemplateMap.put(o.getId(), smsTemplateDetailResDTO);
        });
        List<SmsTemplateRelationModel> smsTemplateRelationModels = smsTemplateRelationMapper.selectByTemplateIds(templateIds);
        smsTemplateRelationModels.forEach(o -> {


            SmsTemplateDetailResDTO smsTemplateDetailResDTO = smsTemplateMap.get(o.getId());
            if (Objects.isNull(smsTemplateDetailResDTO.getProviderSmsTemplates())) {
                smsTemplateDetailResDTO.setUpdatedAt(o.getUpdatedAt());
                smsTemplateDetailResDTO.setUpdatedBy(o.getUpdatedBy());
                smsTemplateDetailResDTO.setProviderSmsTemplates(new ArrayList<>());
            }
            if (null != o.getRelationId()) {
                ProviderSmsTemplateResDTO providerSmsTemplateResDTO = new ProviderSmsTemplateResDTO();
                providerSmsTemplateResDTO.setId(o.getPTemplateId());
                providerSmsTemplateResDTO.setSign(o.getSign());
                providerSmsTemplateResDTO.setContent(o.getPContent());
                providerSmsTemplateResDTO.setCode(o.getCode());
                providerSmsTemplateResDTO.setPriority(o.getPriority());
                providerSmsTemplateResDTO.setRelationId(o.getRelationId());
                providerSmsTemplateResDTO.setStatus(o.getPStatus());
                providerSmsTemplateResDTO.setUpdatedBy(o.getUpdatedBy());
                providerSmsTemplateResDTO.setUpdatedAt(o.getUpdatedAt());
                if (StringUtils.isNotBlank(smsTemplateDetailResDTO.getProviders())
                        && StringUtils.isNotBlank(o.getProviderName())) {
                    smsTemplateDetailResDTO.setProviders(StringUtils.joinWith(",", smsTemplateDetailResDTO.getProviders(), o.getProviderName()));
                }
                if (StringUtils.isBlank(smsTemplateDetailResDTO.getProviders())
                        && StringUtils.isNotBlank(o.getProviderName())) {
                    smsTemplateDetailResDTO.setProviders(o.getProviderName());
                }
                smsTemplateDetailResDTO.getProviderSmsTemplates().add(providerSmsTemplateResDTO);
            }

        });
        List<SmsTemplateDetailResDTO> list = new ArrayList<>(smsTemplateMap.values());
        return pageRes.setRecords(list);
    }

    @Override
    public List<SmsTemplate> getTemplateList() {
        return smsTemplateMapper.selectList(new QueryWrapper<>());
    }


}
