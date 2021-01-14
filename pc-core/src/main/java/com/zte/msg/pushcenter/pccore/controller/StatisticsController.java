package com.zte.msg.pushcenter.pccore.controller;

import com.zte.msg.pushcenter.pccore.dto.DataResponse;
import com.zte.msg.pushcenter.pccore.dto.req.ConditionStatisticsReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.StatisticsReqDTO;
import com.zte.msg.pushcenter.pccore.dto.res.AppStatisticsResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.ProviderStatisticsResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.TypeStatisticsResDTO;
import com.zte.msg.pushcenter.pccore.service.StatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author frp
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/statistics")
@Api(tags = "统计")
@Validated
public class StatisticsController {

    @Resource
    private StatisticsService statisticsService;

    /**
     * 分类统计
     *
     * @param statisticsReqDTO 分类统计搜索
     * @return List<TypeStatisticsResDTO>
     */
    @PostMapping("/type")
    @ApiOperation(value = "分类统计")
    public DataResponse<List<TypeStatisticsResDTO>> typeStatistics(@Valid @RequestBody StatisticsReqDTO statisticsReqDTO) {
        return DataResponse.of(statisticsService.statisticsType(statisticsReqDTO));
    }

    /**
     * 应用统计
     *
     * @param conditionStatisticsReqDTO 应用统计、条件统计搜索
     * @return List<AppStatisticsResDTO>
     */
    @PostMapping("/app")
    @ApiOperation(value = "应用统计、条件统计")
    public DataResponse<List<AppStatisticsResDTO>> appStatistics(@Valid @RequestBody ConditionStatisticsReqDTO conditionStatisticsReqDTO) {
        return DataResponse.of(statisticsService.statisticsAppAndCondition(conditionStatisticsReqDTO));
    }

    /**
     * 消息平台统计
     *
     * @param statisticsReqDTO 消息平台统计搜索
     * @return List<ProviderStatisticsResDTO>
     */
    @PostMapping("/provider")
    @ApiOperation(value = "消息平台统计")
    public DataResponse<List<ProviderStatisticsResDTO>> providerStatistics(@Valid @RequestBody StatisticsReqDTO statisticsReqDTO) {
        return DataResponse.of(statisticsService.statisticsPlatform(statisticsReqDTO));
    }
}
