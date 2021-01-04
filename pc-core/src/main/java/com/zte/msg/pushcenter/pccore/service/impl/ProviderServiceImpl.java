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
    public ProviderResDTO addProvider(ProviderReqDTO providerReqDTO) {
        QueryWrapper<Provider> wrapper = new QueryWrapper<>();
        wrapper.eq("provider_name", providerReqDTO.getProviderName());
        Integer integer = getBaseMapper().selectCount(wrapper);
        if (integer >= 1) {
            throw new CommonException(ErrorCode.PROVIDER_ALREADY_EXIST);
        }
        Provider provider = new Provider();
        BeanUtils.copyProperties(providerReqDTO, provider);
        getBaseMapper().insert(provider);
        ProviderResDTO resDTO = new ProviderResDTO();
        BeanUtils.copyProperties(provider, resDTO);
        return resDTO;
    }

    @Override
    public ProviderResDTO updateProvider(Long providerId, ProviderReqDTO providerReqDTO) {

        Provider exist = getBaseMapper().selectById(providerId);
        if (Objects.isNull(exist)) {
            throw new CommonException(ErrorCode.RESOURCE_NOT_EXIST);
        }
        Provider provider = new Provider();
        BeanUtils.copyProperties(providerReqDTO, provider);
        provider.setId(providerId);
        getBaseMapper().updateById(provider);
        ProviderResDTO resDTO = new ProviderResDTO();
        BeanUtils.copyProperties(provider, resDTO);
        return resDTO;
    }

    @Override
    public void deleteProvider(Long providerId) {
        getBaseMapper().deleteById(providerId);
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
    public Page<ProviderResDTO> getProviders(PageReqDTO pageReqDTO) {

        return getBaseMapper().selectByPage(pageReqDTO.of());
    }
}
