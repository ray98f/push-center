package com.zte.msg.pushcenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zte.msg.pushcenter.dto.req.ProviderReqDTO;
import com.zte.msg.pushcenter.dto.res.ProviderResDTO;
import com.zte.msg.pushcenter.entity.Provider;
import com.zte.msg.pushcenter.enums.ErrorCode;
import com.zte.msg.pushcenter.exception.CommonException;
import com.zte.msg.pushcenter.mapper.ProviderMapper;
import com.zte.msg.pushcenter.service.ProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/21 15:13
 */
@Service
@Slf4j
public class ProviderServiceImpl extends ServiceImpl<ProviderMapper, Provider> implements ProviderService {


    @Override
    public ProviderResDTO addProvider(ProviderReqDTO providerReqDTO) {
        QueryWrapper<Provider> wrapper = new QueryWrapper<>();
        wrapper.eq("provider_name", providerReqDTO.getProviderName());
        Integer integer = getBaseMapper().selectCount(wrapper);
        if (integer >= 1) {
            throw new CommonException(ErrorCode.PROVIDER_EXIST);
        }
        long now = System.currentTimeMillis();
        Provider provider = new Provider();
        BeanUtils.copyProperties(providerReqDTO, provider);
        getBaseMapper().insert(provider);
        ProviderResDTO resDTO = new ProviderResDTO();
        BeanUtils.copyProperties(providerReqDTO, resDTO);
        return resDTO;
    }
}
