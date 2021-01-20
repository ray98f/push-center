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

    void saveOrUpdateEarlyWarnConfig(EarlyWarnConfig earlyWarnConfig);

    EarlyWarnConfig selectEarlyWarnConfig();

}
