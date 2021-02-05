package com.zte.msg.pushcenter.pccore.service.impl;

import com.zte.msg.pushcenter.pccore.dto.req.ConditionStatisticsReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.StatisticsReqDTO;
import com.zte.msg.pushcenter.pccore.dto.res.AppStatisticsResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.ProviderStatisticsResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.ScreenLeftTopResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.TypeStatisticsResDTO;
import com.zte.msg.pushcenter.pccore.entity.App;
import com.zte.msg.pushcenter.pccore.enums.ErrorCode;
import com.zte.msg.pushcenter.pccore.exception.CommonException;
import com.zte.msg.pushcenter.pccore.mapper.AppMapper;
import com.zte.msg.pushcenter.pccore.mapper.StatisticsMapper;
import com.zte.msg.pushcenter.pccore.service.StatisticsService;
import com.zte.msg.pushcenter.pccore.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
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
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private StatisticsMapper statisticsMapper;

    @Autowired
    private AppMapper appMapper;

    @Override
    public List<TypeStatisticsResDTO> statisticsType(StatisticsReqDTO statisticsReqDTO) {
        String[] typeArray = new String[]{"邮件", "短信", "公众号", "App"};
        List<TypeStatisticsResDTO> typeStatisticsResDTOList = statisticsMapper.statisticsType(typeArray, statisticsReqDTO);
        if (null == typeStatisticsResDTOList || typeStatisticsResDTOList.isEmpty()) {
            log.warn("分类统计获取数据为空");
        }
        log.info("分类统计获取成功");
        return typeStatisticsResDTOList;
    }

    @Override
    public List<AppStatisticsResDTO> statisticsAppAndCondition(ConditionStatisticsReqDTO conditionStatisticsReqDTO) {
        List<App> appList = appMapper.listAllApp();
        if (null == appList || appList.isEmpty()) {
            throw new CommonException(ErrorCode.RESOURCE_NOT_EXIST);
        }
        List<AppStatisticsResDTO> appStatisticsResDTOList = statisticsMapper.statisticsAppAndCondition(appList, conditionStatisticsReqDTO);
        if (null == appStatisticsResDTOList || appStatisticsResDTOList.isEmpty()) {
            log.warn("应用统计/条件统计 获取数据为空");
        }
        if (appStatisticsResDTOList != null) {
            log.info("应用统计/条件统计 获取成功");
            appStatisticsResDTOList.removeIf(Objects::isNull);
        }
        return appStatisticsResDTOList;
    }

    @Override
    public List<ProviderStatisticsResDTO> statisticsPlatform(StatisticsReqDTO statisticsReqDTO) {
        List<ProviderStatisticsResDTO> providerStatisticsResDTOList = statisticsMapper.statisticsPlatform(statisticsReqDTO);
        if (null == providerStatisticsResDTOList || providerStatisticsResDTOList.isEmpty()) {
            log.warn("消息平台统计获取数据为空");
        }
        log.info("消息平台统计获取成功");
        return providerStatisticsResDTOList;
    }

}
