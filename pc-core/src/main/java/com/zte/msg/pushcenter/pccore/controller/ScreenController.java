package com.zte.msg.pushcenter.pccore.controller;

import com.zte.msg.pushcenter.pccore.dto.DataResponse;
import com.zte.msg.pushcenter.pccore.dto.res.AppPushDataScreenResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.PushFailedStatisticsScreenResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.PushSuccessDataScreenResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.ScreenLeftTopResDTO;
import com.zte.msg.pushcenter.pccore.service.ScreenService;
import com.zte.msg.pushcenter.pccore.service.StatisticsService;
import com.zte.msg.pushcenter.pccore.utils.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author frp
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/screen")
@Api(tags = "大屏统计")
@Validated
public class ScreenController {

    @Resource
    private StatisticsService statisticsService;

    @Resource
    private ScreenService screenService;

    @GetMapping("/left-top")
    public DataResponse<ScreenLeftTopResDTO> getLeftTop() {
        long now = System.currentTimeMillis();
        return DataResponse.of(statisticsService.getLeftTop(DateUtils.zeroClockOf(now), new Timestamp(now)));
    }

    /**
     * 应用推送信息数据
     *
     * @return List<AppPushDataScreenResDTO>
     */
    @GetMapping("/app")
    @ApiOperation(value = "【大屏】-应用推送信息数据")
    public DataResponse<List<AppPushDataScreenResDTO>> appPushInfoData() {
        return DataResponse.of(screenService.appPushInfoData());
    }

    /**
     * 信息推送成功数据
     *
     * @return List<PushSuccessDataScreenResDTO>
     */
    @GetMapping("/success")
    @ApiOperation(value = "【大屏】-信息推送成功数据")
    public DataResponse<List<PushSuccessDataScreenResDTO>> pushInfoSuccessData() {
        return DataResponse.of(screenService.pushInfoSuccessData());
    }

    /**
     * 信息推送失败统计
     *
     * @return List<PushFailedStatisticsScreenResDTO>
     */
    @GetMapping("/failed")
    @ApiOperation(value = "【大屏】-信息推送失败统计")
    public DataResponse<List<PushFailedStatisticsScreenResDTO>> pushInfoFailedStatistics() {
        return DataResponse.of(screenService.pushInfoFailedStatistics());
    }
}
