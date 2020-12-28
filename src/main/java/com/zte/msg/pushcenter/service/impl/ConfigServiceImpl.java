package com.zte.msg.pushcenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zte.msg.pushcenter.dto.PageReqDTO;
import com.zte.msg.pushcenter.dto.req.ConfigReqDTO;
import com.zte.msg.pushcenter.dto.res.ConfigResDTO;
import com.zte.msg.pushcenter.entity.Config;
import com.zte.msg.pushcenter.enums.ErrorCode;
import com.zte.msg.pushcenter.exception.CommonException;
import com.zte.msg.pushcenter.mapper.ConfigMapper;
import com.zte.msg.pushcenter.service.ConfigService;
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
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements ConfigService {


    @Override
    public ConfigResDTO addConfig(ConfigReqDTO configReqDTO) {
        QueryWrapper<Config> wrapper = new QueryWrapper<>();
        wrapper.eq("provider_name", configReqDTO.getProviderName());
        Integer integer = getBaseMapper().selectCount(wrapper);
        if (integer >= 1) {
            throw new CommonException(ErrorCode.PROVIDER_EXIST);
        }
        Config config = new Config();
        BeanUtils.copyProperties(configReqDTO, config);
        getBaseMapper().insert(config);
        ConfigResDTO resDTO = new ConfigResDTO();
        BeanUtils.copyProperties(config, resDTO);
        return resDTO;
    }

    @Override
    public ConfigResDTO updateConfig(Long configId, ConfigReqDTO configReqDTO) {

        Config config = new Config();
        BeanUtils.copyProperties(configReqDTO, config);
        config.setId(configId);
        getBaseMapper().updateById(config);
        ConfigResDTO resDTO = new ConfigResDTO();
        BeanUtils.copyProperties(config, resDTO);
        return resDTO;
    }

    @Override
    public void deleteConfig(Long configId) {
        getBaseMapper().deleteById(configId);
    }

    @Override
    public ConfigResDTO getConfigById(Long configId) {

        Config config = getBaseMapper().selectById(configId);
        ConfigResDTO configResDTO = new ConfigResDTO();
        BeanUtils.copyProperties(config, configResDTO);
        return configResDTO;
    }

    @Override
    public Page<ConfigResDTO> getConfigs(PageReqDTO pageReqDTO) {

        return getBaseMapper().selectByPage(pageReqDTO.of());
    }
}
