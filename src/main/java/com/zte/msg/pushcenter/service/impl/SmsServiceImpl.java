package com.zte.msg.pushcenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zte.msg.pushcenter.dto.req.SmsConfigReqDTO;
import com.zte.msg.pushcenter.dto.res.SmsConfigResDTO;
import com.zte.msg.pushcenter.entity.SmsConfig;
import com.zte.msg.pushcenter.enums.ErrorCode;
import com.zte.msg.pushcenter.exception.CommonException;
import com.zte.msg.pushcenter.mapper.SmsConfigMapper;
import com.zte.msg.pushcenter.service.SmsConfigService;
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
public class SmsServiceImpl extends ServiceImpl<SmsConfigMapper, SmsConfig> implements SmsConfigService {


    @Override
    public SmsConfigResDTO addSmsConfig(SmsConfigReqDTO reqDTO) {
        SmsConfig smsConfig = new SmsConfig();
        BeanUtils.copyProperties(reqDTO, smsConfig);
        getBaseMapper().insert(smsConfig);
        SmsConfigResDTO resDTO = new SmsConfigResDTO();
        BeanUtils.copyProperties(smsConfig, resDTO);
        return resDTO;
    }
}
