package com.zte.msg.pushcenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zte.msg.pushcenter.dto.req.SmsProviderConfigReqDTO;
import com.zte.msg.pushcenter.dto.res.SmsProviderConfigResDTO;
import com.zte.msg.pushcenter.entity.SmsProviderConfig;
import com.zte.msg.pushcenter.enums.ErrorCode;
import com.zte.msg.pushcenter.exception.CommonException;
import com.zte.msg.pushcenter.mapper.SmsProviderConfigMapper;
import com.zte.msg.pushcenter.service.SmsProviderConfigService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/22 14:54
 */
@Service
public class SmsProviderServiceImpl extends ServiceImpl<SmsProviderConfigMapper, SmsProviderConfig> implements SmsProviderConfigService {


    @Override
    public SmsProviderConfigResDTO addSmsProviderConfig(SmsProviderConfigReqDTO reqDTO) {

        Integer count = getBaseMapper().selectCount(new QueryWrapper<SmsProviderConfig>()
                .eq("provider_id", reqDTO.getProviderId()));
        if (count >= 1) {
//            throw new CommonException(ErrorCode.DATA_EXIST);
        }

        SmsProviderConfig smsProviderConfig = new SmsProviderConfig();
        BeanUtils.copyProperties(reqDTO, smsProviderConfig);
        getBaseMapper().insert(smsProviderConfig);
        SmsProviderConfigResDTO resDTO = new SmsProviderConfigResDTO();
        BeanUtils.copyProperties(smsProviderConfig, resDTO);
        return resDTO;
    }
}
