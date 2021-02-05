package com.zte.msg.pushcenter.pccore.service;

import com.zte.msg.pushcenter.pccore.dto.req.ConditionStatisticsReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.StatisticsReqDTO;
import com.zte.msg.pushcenter.pccore.dto.res.AppStatisticsResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.ProviderStatisticsResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.ScreenLeftTopResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.TypeStatisticsResDTO;

import java.sql.Timestamp;
import java.util.List;

/**
 * description:
 *
 * @author frp
 * @version 1.0
 * @date 2020/12/23 15:42
 */
public interface StatisticsService {

    /**
     * 分类统计
     *
     * @param statisticsReqDTO
     * @return
     */
    List<TypeStatisticsResDTO> statisticsType(StatisticsReqDTO statisticsReqDTO);

    /**
     * 应用统计、条件统计搜索
     *
     * @param conditionStatisticsReqDTO
     * @return
     */
    List<AppStatisticsResDTO> statisticsAppAndCondition(ConditionStatisticsReqDTO conditionStatisticsReqDTO);

    /**
     * 消息平台统计搜索
     *
     * @param statisticsReqDTO
     * @return
     */
    List<ProviderStatisticsResDTO> statisticsPlatform(StatisticsReqDTO statisticsReqDTO);

    ScreenLeftTopResDTO getLeftTop(Timestamp start, Timestamp end);

}
