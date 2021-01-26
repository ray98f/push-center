package com.zte.msg.pushcenter.pccore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zte.msg.pushcenter.pccore.core.pusher.SmsPusher;
import com.zte.msg.pushcenter.pccore.core.pusher.WeChatPusher;
import com.zte.msg.pushcenter.pccore.dto.PageReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.*;
import com.zte.msg.pushcenter.pccore.dto.res.ProviderSmsTemplateResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.SmsTemplateDetailResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.WeChatTemplateResDTO;
import com.zte.msg.pushcenter.pccore.entity.*;
import com.zte.msg.pushcenter.pccore.enums.ErrorCode;
import com.zte.msg.pushcenter.pccore.exception.CommonException;
import com.zte.msg.pushcenter.pccore.mapper.*;
import com.zte.msg.pushcenter.pccore.model.SmsTemplateRelationModel;
import com.zte.msg.pushcenter.pccore.service.TemplateService;
import com.zte.msg.pushcenter.pccore.utils.Constants;
import com.zte.msg.pushcenter.pccore.utils.PatternUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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
    private SmsPusher smsPusher;

    @Resource
    private WeChatPusher weChatPusher;

    @Resource
    private SmsTemplateMapper smsTemplateMapper;

    @Resource
    private ProviderSmsTemplateMapper providerSmsTemplateMapper;

    @Resource
    private SmsTemplateRelationMapper smsTemplateRelationMapper;

    @Resource
    private WeChatTemplateMapper weChatTemplateMapper;

    @Resource
    private ProviderMapper providerMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addSmsTemplate(SmsTemplateReqDTO smsTemplateReqDTO) {
        if (PatternUtils.getParams(smsTemplateReqDTO.getContent()).size() != smsTemplateReqDTO.getParams().length) {
            throw new CommonException(ErrorCode.SMS_TEMPLATE_PARAMS_NOT_MATCH);
        }
        String params = StringUtils.join(smsTemplateReqDTO.getParams(), Constants.COMMA_EN);
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
        smsTemplateRelationMapper.delete(new LambdaQueryWrapper<SmsTemplateRelation>()
                .in(SmsTemplateRelation::getSmsTemplateId, Arrays.asList(templateIds)));
        // 删除模板时，刷新内存中的模板信息
        smsPusher.flushConfig(templateIds, true);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addProviderSmsTemplateRelate(Long templateId, SmsTemplateRelateProviderReqDTO reqDTO) {
        SmsTemplate smsTemplate = smsTemplateMapper.selectById(templateId);
        if (Objects.isNull(smsTemplate)) {
            throw new CommonException(ErrorCode.SMS_TEMPLATE_NOT_EXIST);
        }
        if (smsTemplateRelationMapper.selectCount(new LambdaQueryWrapper<SmsTemplateRelation>()
                .eq(SmsTemplateRelation::getSmsTemplateId, templateId)
                .eq(SmsTemplateRelation::getProviderTemplateId, reqDTO.getPTemplateId())) >= 1) {
            throw new CommonException(ErrorCode.SMS_TEMPLATE_RELATION_ALREADY_EXIST);
        }
        if (smsTemplateRelationMapper.selectCount(new LambdaQueryWrapper<SmsTemplateRelation>()
                .eq(SmsTemplateRelation::getPriority, reqDTO.getPriority())
                .eq(SmsTemplateRelation::getSmsTemplateId, templateId)) >= 1) {
            throw new CommonException(ErrorCode.SMS_TEMPLATE_RELATION_PRIORITY_EXIST);
        }
        ProviderSmsTemplate providerSmsTemplate = providerSmsTemplateMapper.selectById(reqDTO.getPTemplateId());
        if (PatternUtils.getParams(providerSmsTemplate.getContent()).size() != smsTemplate.getParams().split(Constants.COMMA_EN).length) {
            throw new CommonException(ErrorCode.SMS_TEMPLATE_PARAMS_NOT_MATCH);
        }
        SmsTemplateRelation relation = new SmsTemplateRelation();
        relation.setPriority(reqDTO.getPriority());
        relation.setProviderTemplateId(reqDTO.getPTemplateId());
        relation.setSmsTemplateId(templateId);
        smsTemplateRelationMapper.insert(relation);

        // 关联模板时，刷新内存中的模板信息
        smsPusher.flushConfig(templateId);
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
        if (smsTemplateRelationMapper.selectCount(new LambdaQueryWrapper<SmsTemplateRelation>()
                .eq(SmsTemplateRelation::getPriority, reqDTO.getPriority())) >= 0) {
            throw new CommonException(ErrorCode.SMS_TEMPLATE_RELATION_ALREADY_EXIST);
        }
        ProviderSmsTemplate providerSmsTemplate = providerSmsTemplateMapper.selectById(relation.getProviderTemplateId());
        providerSmsTemplate.setStatus(reqDTO.getStatus());
        providerSmsTemplateMapper.updateById(providerSmsTemplate);
        relation.setId(reqDTO.getRelationId());
        relation.setPriority(reqDTO.getPriority());
        relation.setSmsTemplateId(templateId);
        smsTemplateRelationMapper.updateById(relation);

        // 关联模板时，刷新内存中的模板信息
        smsPusher.flushConfig(templateId);
    }

    @Override
    public void deleteProviderSmsTemplateRelate(Long templateId, Long[] ids) {
        if (Objects.isNull(smsTemplateMapper.selectById(templateId))) {
            throw new CommonException(ErrorCode.SMS_TEMPLATE_NOT_EXIST);
        }
        smsTemplateRelationMapper.deleteBatchIds(Arrays.asList(ids));

        // 删除关联模板时，更新配置，
        smsPusher.flushConfig(templateId);
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
        LambdaQueryWrapper<SmsTemplate> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(content)) {
            wrapper.like(SmsTemplate::getContent, content);
        }
        if (null != templateId) {
            wrapper.eq(SmsTemplate::getId, templateId);
        }
        if (null != status) {
            wrapper.eq(SmsTemplate::getStatus, status);
        }

        Page<SmsTemplate> smsTemplatePage = smsTemplateMapper.selectPage(pageReqDTO.of(), wrapper);
        List<SmsTemplate> templates = smsTemplatePage.getRecords();

        Page<SmsTemplateDetailResDTO> pageRes = new Page<>();
        BeanUtils.copyProperties(smsTemplatePage, pageRes);
        List<Long> templateIds = new ArrayList<>();
        Map<Long, SmsTemplateDetailResDTO> smsTemplateMap = new HashMap<>(16);
        if (templates.size() == 0) {
            return pageRes;
        }
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

    @Override
    public void addWeChatTemplate(WeChatTemplateReqDTO reqDTO) {

        if (Objects.isNull(providerMapper.selectById(reqDTO.getProviderId()))) {
            throw new CommonException(ErrorCode.PROVIDER_NOT_EXIST);
        }
        WeChatTemplate weChatTemplate = new WeChatTemplate();
        BeanUtils.copyProperties(reqDTO, weChatTemplate);
        weChatTemplateMapper.insert(weChatTemplate);
        weChatPusher.flushConfig(weChatTemplate.getId());
    }

    @Override
    public void updateWeChatTemplate(WeChatTemplateUpdateReqDTO reqDTO) {
        if (Objects.isNull(providerMapper.selectById(reqDTO.getProviderId()))) {
            throw new CommonException(ErrorCode.PROVIDER_NOT_EXIST);
        }
        WeChatTemplate weChatTemplate = new WeChatTemplate();
        BeanUtils.copyProperties(reqDTO, weChatTemplate);
        weChatTemplateMapper.updateById(weChatTemplate);
        weChatPusher.flushConfig(weChatTemplate.getId());
    }

    @Override
    public WeChatTemplateResDTO getWeChatTemplate(Long templateId) {

        WeChatTemplate weChatTemplate = weChatTemplateMapper.selectById(templateId);
        WeChatTemplateResDTO weChatTemplateResDTO = new WeChatTemplateResDTO();
        BeanUtils.copyProperties(weChatTemplate, weChatTemplateResDTO);
        return weChatTemplateResDTO;
    }

    @Override
    public void deleteWeChatTemplates(Long[] ids) {

        if (ids.length == 0) {
            return;
        }
        weChatTemplateMapper.deleteBatchIds(Arrays.asList(ids));

        weChatPusher.flushConfig(ids, true);
    }

    @Override
    public Page<WeChatTemplateResDTO> getWeChatTemplates(PageReqDTO page,
                                                         Long templateId,
                                                         String providerName,
                                                         Integer status) {
        LambdaQueryWrapper<WeChatTemplate> wrapper = new LambdaQueryWrapper<>();
        if (!Objects.isNull(templateId)) {
            wrapper.eq(WeChatTemplate::getId, templateId);
        }
        if (!Objects.isNull(providerName)) {
            wrapper.like(WeChatTemplate::getProviderName, providerName);
        }
        if (!Objects.isNull(status)) {
            wrapper.eq(WeChatTemplate::getStatus, status);
        }
        Page<WeChatTemplate> result = weChatTemplateMapper.selectPage(page.of(), wrapper);
        List<WeChatTemplate> records = result.getRecords();
        Page<WeChatTemplateResDTO> resPage = new Page<>();
        BeanUtils.copyProperties(result, resPage);
        if (CollectionUtils.isEmpty(records)) {
            return resPage;
        }
        List<WeChatTemplateResDTO> resList = new ArrayList<>(records.size());
        records.forEach(o -> {
            WeChatTemplateResDTO resDTO = new WeChatTemplateResDTO();
            BeanUtils.copyProperties(o, resDTO);
            resList.add(resDTO);
        });
        return resPage.setRecords(resList);
    }


}
