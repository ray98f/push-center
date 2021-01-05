package com.zte.msg.pushcenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zte.msg.pushcenter.core.pusher.SmsPusher;
import com.zte.msg.pushcenter.dto.PageReqDTO;
import com.zte.msg.pushcenter.dto.req.SmsConfigReqDTO;
import com.zte.msg.pushcenter.dto.res.SmsConfigDetailResDTO;
import com.zte.msg.pushcenter.entity.SmsConfig;
import com.zte.msg.pushcenter.enums.ErrorCode;
import com.zte.msg.pushcenter.exception.CommonException;
import com.zte.msg.pushcenter.mapper.SmsConfigMapper;
import com.zte.msg.pushcenter.service.SmsConfigService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/22 14:54
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SmsConfigServiceImpl extends ServiceImpl<SmsConfigMapper, SmsConfig> implements SmsConfigService {

    @Override
    public SmsConfig getById(Long id) {

        return getBaseMapper().selectById(id);
    }

    @Override
    public SmsConfigDetailResDTO getSmsConfig(Long id) {
        return getBaseMapper().selectDetailById(id);
    }

    @Override
    public SmsConfigDetailResDTO addSmsConfig(SmsConfigReqDTO reqDTO) {
        if (getBaseMapper().selectCount(new QueryWrapper<SmsConfig>().eq("name", reqDTO.getName())) >= 1) {
            throw new CommonException(ErrorCode.SMS_CONFIG_NAME_EXIST);
        }
        SmsConfig config = new SmsConfig();
        BeanUtils.copyProperties(reqDTO, config);
        getBaseMapper().insert(config);
        return getBaseMapper().selectDetailById(config.getId());
    }

    @Override
    public void updateSmsConfig(Long smsConfigId, SmsConfigReqDTO reqDTO) {
        if (Objects.isNull(getBaseMapper().selectById(smsConfigId))) {
            throw new CommonException(ErrorCode.RESOURCE_NOT_EXIST);
        }
        SmsConfig config = new SmsConfig();
        BeanUtils.copyProperties(reqDTO, config);
        config.setId(smsConfigId);
        getBaseMapper().updateById(config);
    }

    @Override
    public void deleteSmsConfig(Long id) {
        if (Objects.isNull(getBaseMapper().selectById(id))) {
            throw new CommonException(ErrorCode.RESOURCE_NOT_EXIST);
        }
        getBaseMapper().deleteById(id);
    }

    @Override
    public Page<SmsConfigDetailResDTO> getSmsConfigs(PageReqDTO page) {
        return getBaseMapper().selectByPage(page.of());
    }

    @Override
    public List<SmsPusher.ConfigDetail> selectConfigDetail(Integer type) {
        return getBaseMapper().selectConfigDetail(type);
    }

}
