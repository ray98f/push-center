package com.zte.msg.pushcenter.pccore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zte.msg.pushcenter.pccore.dto.PageReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.ProviderReqDTO;
import com.zte.msg.pushcenter.pccore.dto.res.ProviderResDTO;
import com.zte.msg.pushcenter.pccore.entity.Provider;
import com.zte.msg.pushcenter.pccore.enums.ErrorCode;
import com.zte.msg.pushcenter.pccore.exception.CommonException;
import com.zte.msg.pushcenter.pccore.mapper.ProviderMapper;
import com.zte.msg.pushcenter.pccore.service.ProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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


    @Override
    public void addProvider(ProviderReqDTO providerReqDTO) {

        // TODO: 2021/1/6  新增供应商时，需要刷新缓存的配置信息

        Integer integer = getBaseMapper().selectCount(new QueryWrapper<Provider>()
                .eq("provider_name", providerReqDTO.getProviderName())
                .eq("type", providerReqDTO.getType()));
        if (integer >= 1) {
            throw new CommonException(ErrorCode.PROVIDER_ALREADY_EXIST);
        }
        Provider provider = new Provider();
        BeanUtils.copyProperties(providerReqDTO, provider);
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
        getBaseMapper().updateById(provider);
    }

    @Override
    public void deleteProvider(Long[] providerIds) {
        getBaseMapper().deleteBatchIds(Arrays.asList(providerIds));
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
