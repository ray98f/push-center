package com.zte.msg.pushcenter.pccore.service;

import com.github.pagehelper.PageInfo;
import com.zte.msg.pushcenter.pccore.dto.req.StatisticsReqDTO;
import com.zte.msg.pushcenter.pccore.entity.EarlyWarnConfig;
import com.zte.msg.pushcenter.pccore.entity.EarlyWarnInfo;

import java.util.List;

/**
 * description:
 *
 * @author frp
 * @version 1.0
 * @date 2020/12/23 15:42
 */
public interface EarlyWarnService {

    void insertEarlyWarnConfig(EarlyWarnConfig earlyWarnConfig);

    PageInfo<EarlyWarnInfo> listEarlyWarnInfo(StatisticsReqDTO statisticsReqDTO);

}
