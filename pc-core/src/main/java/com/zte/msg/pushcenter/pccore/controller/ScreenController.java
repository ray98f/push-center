package com.zte.msg.pushcenter.pccore.controller;

import com.zte.msg.pushcenter.pccore.dto.DataResponse;
import com.zte.msg.pushcenter.pccore.dto.res.ScreenLeftTopResDTO;
import com.zte.msg.pushcenter.pccore.service.StatisticsService;
import com.zte.msg.pushcenter.pccore.utils.DateUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Timestamp;

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

    @GetMapping("/left-top")
    public DataResponse<ScreenLeftTopResDTO> getLeftTop() {
        long now = System.currentTimeMillis();
        return DataResponse.of(statisticsService.getLeftTop(DateUtils.zeroClockOf(now), new Timestamp(now)));
    }

}
