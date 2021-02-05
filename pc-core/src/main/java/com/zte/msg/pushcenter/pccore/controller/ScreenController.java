package com.zte.msg.pushcenter.pccore.controller;

import com.zte.msg.pushcenter.pccore.dto.DataResponse;
import com.zte.msg.pushcenter.pccore.dto.res.*;
import com.zte.msg.pushcenter.pccore.service.ScreenService;
import com.zte.msg.pushcenter.pccore.utils.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
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

    @GetMapping("/left-top")
    @ApiOperation("获取大屏面板左上角当天和当周各推送数据")
    public DataResponse<ScreenLeftTopResDTO> getLeftTop() {

        ScreenLeftTopResDTO screenLeftTopResDTO = new ScreenLeftTopResDTO();
        screenLeftTopResDTO.setSmsDaily(115);
        screenLeftTopResDTO.setAppDaily(15);
        screenLeftTopResDTO.setWechatDaily(454);
        screenLeftTopResDTO.setMailDaily(887);
        screenLeftTopResDTO.setSmsWeekly(5457);
        screenLeftTopResDTO.setAppWeekly(2848);
        screenLeftTopResDTO.setWechatWeekly(1989);
        screenLeftTopResDTO.setMailWeekly(3374);

        return DataResponse.of(screenLeftTopResDTO);
//        return DataResponse.of(screenService.getLeftTop(System.currentTimeMillis()));
    }

    @GetMapping("/push-delay")
    @ApiOperation("信息推送延迟数据")
    public DataResponse<PushDelayResDTO> getPushDelay() {

        List<PushDelayResDTO.PushDelay> smsPushDelays = new ArrayList<>();

        List<PushDelayResDTO.PushDelay> mailPushDelays = new ArrayList<>();

        List<PushDelayResDTO.PushDelay> appPushDelays = new ArrayList<>();

        List<PushDelayResDTO.PushDelay> wechatDelays = new ArrayList<>();
        long mill = System.currentTimeMillis();
        for (int i = 0; i < 8; i++) {
            Timestamp timestamp = new Timestamp(mill - mill % Constants.DAY_MILL + 15 * 60 * 1000);
            PushDelayResDTO.PushDelay pushDelay = new PushDelayResDTO.PushDelay();
            pushDelay.setTime(timestamp);
            pushDelay.setDelayAvg(10 + i * 5);
            smsPushDelays.add(pushDelay);

            PushDelayResDTO.PushDelay pushDelay1 = new PushDelayResDTO.PushDelay();
            pushDelay1.setTime(timestamp);
            pushDelay1.setDelayAvg(10 + i * 4);
            mailPushDelays.add(pushDelay1);

            PushDelayResDTO.PushDelay pushDelay2 = new PushDelayResDTO.PushDelay();
            pushDelay2.setTime(timestamp);
            pushDelay2.setDelayAvg(10 + i * 3);
            appPushDelays.add(pushDelay2);

            PushDelayResDTO.PushDelay pushDelay3 = new PushDelayResDTO.PushDelay();
            pushDelay3.setTime(timestamp);
            pushDelay3.setDelayAvg(10 + i * 3);
            wechatDelays.add(pushDelay3);
        }
        PushDelayResDTO pushDelayResDTO = new PushDelayResDTO();
        pushDelayResDTO.setSmsPushDelays(smsPushDelays);
        pushDelayResDTO.setMailPushDelays(mailPushDelays);
        pushDelayResDTO.setAppPushDelays(appPushDelays);
        pushDelayResDTO.setWechatDelays(wechatDelays);
        return DataResponse.of(pushDelayResDTO);
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
