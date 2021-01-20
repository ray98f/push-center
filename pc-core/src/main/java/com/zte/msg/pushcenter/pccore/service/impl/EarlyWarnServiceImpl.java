package com.zte.msg.pushcenter.pccore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zte.msg.pushcenter.pccore.core.warn.WarnHandler;
import com.zte.msg.pushcenter.pccore.entity.EarlyWarnConfig;
import com.zte.msg.pushcenter.pccore.mapper.EarlyWarnConfigMapper;
import com.zte.msg.pushcenter.pccore.service.EarlyWarnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * description:
 *
 * @author frp
 * @version 1.0
 * @date 2020/12/28 15:42
 */
@Service
@Slf4j
public class EarlyWarnServiceImpl extends ServiceImpl<EarlyWarnConfigMapper, EarlyWarnConfig> implements EarlyWarnService {

    @Resource
    private WarnHandler warnHandler;


    @Override
    public void saveOrUpdateEarlyWarnConfig(EarlyWarnConfig earlyWarnConfig) {

        if (Objects.isNull(earlyWarnConfig.getId())) {
            getBaseMapper().insert(earlyWarnConfig);
        } else {
            getBaseMapper().updateById(earlyWarnConfig);
        }
        warnHandler.updateWarnConfig();
    }

    @Override
    public EarlyWarnConfig selectEarlyWarnConfig() {
        return getBaseMapper().selectOne(new QueryWrapper<>());
    }
}
