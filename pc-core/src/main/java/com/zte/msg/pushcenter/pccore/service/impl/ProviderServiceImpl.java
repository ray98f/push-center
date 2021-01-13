package com.zte.msg.pushcenter.pccore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import com.zte.msg.pushcenter.pccore.enums.PushMethods;
import com.zte.msg.pushcenter.pccore.exception.CommonException;
import com.zte.msg.pushcenter.pccore.mapper.ProviderMapper;
import com.zte.msg.pushcenter.pccore.mapper.ProviderSmsTemplateMapper;
import com.zte.msg.pushcenter.pccore.mapper.SmsTemplateRelationMapper;
import com.zte.msg.pushcenter.pccore.model.SmsConfigModel;
import com.zte.msg.pushcenter.pccore.service.ProviderService;
import com.zte.msg.pushcenter.pccore.utils.PushConfigUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        Integer integer = getBaseMapper().selectCount(new QueryWrapper<Provider>()
                .eq("provider_name", providerReqDTO.getProviderName())
                .eq("type", providerReqDTO.getType()));
        if (integer >= 1) {
            throw new CommonException(ErrorCode.PROVIDER_ALREADY_EXIST);
        }
        Provider provider = new Provider();
        BeanUtils.copyProperties(providerReqDTO, provider);
        if (PushMethods.valueOf(providerReqDTO.getType()) != PushMethods.MAIL) {
            provider.setScriptTag(PushConfigUtils.getTag());
        }
        getBaseMapper().insert(provider);
        // 刷新配置
        flusher.flush(provider);
    }

    @Override
    public void updateProvider(Long providerId, ProviderReqDTO providerReqDTO) {

        Provider exist = getBaseMapper().selectById(providerId);
        if (Objects.isNull(exist)) {
            throw new CommonException(ErrorCode.RESOURCE_NOT_EXIST);
        }
        Provider provider = new Provider();
        BeanUtils.copyProperties(providerReqDTO, provider);
        if (PushMethods.valueOf(providerReqDTO.getType()) != PushMethods.MAIL) {
            provider.setScriptTag(PushConfigUtils.getTag());
        }
        provider.setId(providerId);
        getBaseMapper().updateById(provider);
        // 刷新配置
        flusher.flush(provider);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteProvider(Long[] providerIds) {

        List<ProviderSmsTemplate> providerSmsTemplates = providerSmsTemplateMapper.selectList(new QueryWrapper<ProviderSmsTemplate>()
                .in("provider_id", Arrays.asList(providerIds)));

        List<Long> providerSmsTemplateIds = providerSmsTemplates.stream()
                .map(ProviderSmsTemplate::getId).collect(Collectors.toList());
        // 删除消息平台表中的模版
        providerSmsTemplateMapper.deleteBatchIds(providerSmsTemplateIds);

        // 删除短信关联映射表中的数据
        smsTemplateRelationMapper.delete(new QueryWrapper<SmsTemplateRelation>()
                .in("provider_template_id", providerSmsTemplateIds));

        List<Long> ids = Arrays.asList(providerIds);
        List<Provider> providers = getBaseMapper().selectBatchIds(ids);

        // 刷新脚本和配置
        flusher.flush(providers, true);
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

        QueryWrapper<Provider> wrapper = new QueryWrapper<>();
        if (Objects.nonNull(provider)) {
            wrapper.like("provider_name", provider);
        }
        if (Objects.nonNull(type)) {
            wrapper.eq("type", type);
        }
        if (Objects.nonNull(status)) {
            wrapper.eq("status", status);
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

    @Override
    public List<SmsConfigModel> getAllSmsConfigForInit() {
        return getBaseMapper().selectAllSmsConfigForInit();
    }

    @Override
    public List<Provider> getProviderByType(Integer type) {
        return getBaseMapper().selectList(new QueryWrapper<Provider>().eq("type", type));
    }

    @Override
    public List<SmsConfigModel> getSmsConfigForFlush(List<Provider> providers) {

        List<Long> providerIds = providers.stream().map(Provider::getId).collect(Collectors.toList());
        return getBaseMapper().selectSmsConfigForFlushByProviderIds(providerIds);
    }
}
