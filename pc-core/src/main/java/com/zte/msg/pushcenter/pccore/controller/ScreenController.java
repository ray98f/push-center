package com.zte.msg.pushcenter.pccore.controller;

import com.zte.msg.pushcenter.pccore.dto.DataResponse;
import com.zte.msg.pushcenter.pccore.dto.PageReqDTO;
import com.zte.msg.pushcenter.pccore.dto.PageResponse;
import com.zte.msg.pushcenter.pccore.dto.res.*;
import com.zte.msg.pushcenter.pccore.entity.EarlyWarnInfo;
import com.zte.msg.pushcenter.pccore.service.EarlyWarnInfoService;
import com.zte.msg.pushcenter.pccore.service.ScreenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
    private ScreenService screenService;

    @Resource
    private EarlyWarnInfoService earlyWarnInfoService;

    @GetMapping("/left-top")
    @ApiOperation("获取大屏面板左上角当天和当周各推送数据")
    public DataResponse<ScreenLeftTopResDTO> getLeftTop() {

        return DataResponse.of(screenService.getLeftTop(System.currentTimeMillis()));
    }

    @GetMapping("/push-delay")
    @ApiOperation("信息推送延迟数据")
    public DataResponse<PushDelayResDTO> getPushDelay() {
        return DataResponse.of(screenService.getAvgDelayByFifteenMinute(System.currentTimeMillis()));
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
    @ApiOperation(value = "【大屏】-信息推送成功数据 & 【大屏】-今日信息推送数据")
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

    /**
     * 预警信息
     *
     * @param pageReqDTO
     * @return
     */
    @GetMapping("/warn")
    @ApiOperation(value = "【大屏】-预警信息")
    public PageResponse<EarlyWarnInfo> listEarlyWarnInfo(@Valid PageReqDTO pageReqDTO,
                                                         @RequestParam(required = false) Timestamp startTime,
                                                         @RequestParam(required = false) Timestamp endTime) {
        return PageResponse.of(earlyWarnInfoService.getWarnInfoByPage(pageReqDTO, startTime, endTime));
    }

    /**
     * 预警信息
     *
     * @return
     */
    @GetMapping("/warn/handle")
    @ApiOperation(value = "【大屏】-处理预警信息")
    public <T> DataResponse<T> handleWarnInfo(@Valid @RequestParam @NotNull(message = "32000006") Long id) {
        earlyWarnInfoService.handleWarnInfo(id);
        return DataResponse.success();
    }
}
