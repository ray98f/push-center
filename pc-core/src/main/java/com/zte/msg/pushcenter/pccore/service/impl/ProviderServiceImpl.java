package com.zte.msg.pushcenter.pccore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zte.msg.pushcenter.pccore.core.Flusher;
import com.zte.msg.pushcenter.pccore.dto.PageReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.ProviderReqDTO;
import com.zte.msg.pushcenter.pccore.dto.res.ProviderResDTO;
import com.zte.msg.pushcenter.pccore.entity.Provider;
import com.zte.msg.pushcenter.pccore.entity.ProviderSmsTemplate;
import com.zte.msg.pushcenter.pccore.entity.SmsTemplateRelation;
import com.zte.msg.pushcenter.pccore.enums.ErrorCode;
import com.zte.msg.pushcenter.pccore.exception.CommonException;
import com.zte.msg.pushcenter.pccore.mapper.ProviderMapper;
import com.zte.msg.pushcenter.pccore.mapper.ProviderSmsTemplateMapper;
import com.zte.msg.pushcenter.pccore.mapper.SmsTemplateRelationMapper;
import com.zte.msg.pushcenter.pccore.service.ProviderService;
import com.zte.msg.pushcenter.pccore.utils.PushConfigUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/21 15:13
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class ProviderServiceImpl extends ServiceImpl<ProviderMapper, Provider> implements ProviderService {

    @Resource
    private Flusher flusher;

    @Resource
    private ProviderSmsTemplateMapper providerSmsTemplateMapper;

    @Resource
    private SmsTemplateRelationMapper smsTemplateRelationMapper;

    @Override
    public void addProvider(ProviderReqDTO providerReqDTO) {

        Integer integer = getBaseMapper().selectCount(new LambdaQueryWrapper<Provider>()
                .eq(Provider::getProviderName, providerReqDTO.getProviderName())
                .eq(Provider::getType, providerReqDTO.getType()));
        if (integer >= 1) {
            throw new CommonException(ErrorCode.PROVIDER_ALREADY_EXIST);
        }
        Provider provider = new Provider();
        BeanUtils.copyProperties(providerReqDTO, provider);

        flusher.flush(provider);
        getBaseMapper().insert(provider);
    }

    @Override
    public void updateProvider(Long providerId, ProviderReqDTO providerReqDTO) {

        Provider exist = getBaseMapper().selectById(providerId);
        if (Objects.isNull(exist)) {
            throw new CommonException(ErrorCode.RESOURCE_NOT_EXIST);
        }
        Provider provider = new Provider();
        BeanUtils.copyProperties(providerReqDTO, provider);
        provider.setId(providerId);
        if (Objects.isNull(exist.getScriptTag()) && StringUtils.isNotBlank(providerReqDTO.getScriptContext())) {
            exist.setScriptTag(PushConfigUtils.getTag());
        }
        provider.setScriptTag(exist.getScriptTag());
        flusher.flush(provider);
        getBaseMapper().updateById(provider);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteProvider(Long[] providerIds) {

        List<ProviderSmsTemplate> providerSmsTemplates = providerSmsTemplateMapper.selectList(new LambdaQueryWrapper<ProviderSmsTemplate>()
                .in(ProviderSmsTemplate::getProviderId, Arrays.asList(providerIds)));

        if (!CollectionUtils.isEmpty(providerSmsTemplates)) {
            List<Long> providerSmsTemplateIds = providerSmsTemplates.stream()
                    .map(ProviderSmsTemplate::getId).collect(Collectors.toList());
            // 删除消息平台表中的模版
            providerSmsTemplateMapper.deleteBatchIds(providerSmsTemplateIds);
            // 删除短信关联映射表中的数据
            smsTemplateRelationMapper.delete(new LambdaQueryWrapper<SmsTemplateRelation>()
                    .in(SmsTemplateRelation::getProviderTemplateId, providerSmsTemplateIds));
        }

        List<Long> ids = Arrays.asList(providerIds);
        List<Provider> providers = getBaseMapper().selectBatchIds(ids);

        // 刷新脚本和配置
        flusher.flush(true, providers.toArray(new Provider[]{}));
        getBaseMapper().deleteBatchIds(ids);

    }

    @Override
    public ProviderResDTO getProviderById(Long providerId) {

        Provider provider = getBaseMapper().selectById(providerId);
        if (!Objects.isNull(provider)) {
            ProviderResDTO providerResDTO = new ProviderResDTO();
            BeanUtils.copyProperties(provider, providerResDTO);
            return providerResDTO;
        }
        return null;
    }

    @Override
    public Page<ProviderResDTO> getProviders(String provider,
                                             Integer type,
                                             Integer status,
                                             PageReqDTO pageReqDTO) {

        LambdaQueryWrapper<Provider> wrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(provider)) {
            wrapper.like(Provider::getProviderName, provider);
        }
        if (Objects.nonNull(type)) {
            wrapper.eq(Provider::getType, type);
        }
        Page<Provider> providerPage = getBaseMapper().selectPage(pageReqDTO.of(), wrapper);
        Page<ProviderResDTO> dtoPage = new Page<>();
        BeanUtils.copyProperties(providerPage, dtoPage);
        List<Provider> records = providerPage.getRecords();
        List<ProviderResDTO> list = new ArrayList<>(records.size());
        records.forEach(o -> {
            ProviderResDTO providerResDTO = new ProviderResDTO();
            BeanUtils.copyProperties(o, providerResDTO);
            list.add(providerResDTO);
        });

        return dtoPage.setRecords(list);
    }

}
