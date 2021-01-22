package com.zte.msg.pushcenter.pccore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zte.msg.pushcenter.pccore.entity.EarlyWarnConfig;

/**
 * description:
 *
 * @author frp
 * @version 1.0
 * @date 2020/12/23 15:42
 */
public interface EarlyWarnService extends IService<EarlyWarnConfig> {

    /**
     * 更新预警配置
     *
     * @param earlyWarnConfig
     */
    void saveOrUpdateEarlyWarnConfig(EarlyWarnConfig earlyWarnConfig);

    /**
     * 查询预警配置
     *
     * @return
     */
    EarlyWarnConfig selectEarlyWarnConfig();

}
