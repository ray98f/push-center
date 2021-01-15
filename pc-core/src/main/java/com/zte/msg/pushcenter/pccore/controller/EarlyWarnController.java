package com.zte.msg.pushcenter.pccore.controller;

import com.zte.msg.pushcenter.pccore.dto.DataResponse;
import com.zte.msg.pushcenter.pccore.dto.PageResponse;
import com.zte.msg.pushcenter.pccore.dto.req.StatisticsReqDTO;
import com.zte.msg.pushcenter.pccore.entity.EarlyWarnConfig;
import com.zte.msg.pushcenter.pccore.entity.EarlyWarnInfo;
import com.zte.msg.pushcenter.pccore.service.EarlyWarnService;
import com.zte.msg.pushcenter.pccore.utils.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * description:
 *
 * @author frp
 * @version 1.0
 * @date 2021/1/15 11:03
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/warn")
@Api(tags = "预警")
@Validated
public class EarlyWarnController {

    @Resource
    private EarlyWarnService earlyWarnService;

    /**
     * 新增预警配置
     * @param earlyWarnConfig 预警配置信息
     * @return <T>
     */
    @PutMapping("/config")
    @ApiOperation(value = "新增预警配置")
    public <T> DataResponse<T> insertEarlyWarnConfig(@Valid @RequestBody EarlyWarnConfig earlyWarnConfig){
        earlyWarnConfig.setCreatedBy(TokenUtil.getCurrentUserName());
        earlyWarnConfig.setUpdatedBy(TokenUtil.getCurrentUserName());
        earlyWarnService.insertEarlyWarnConfig(earlyWarnConfig);
        return DataResponse.success();
    }

    /**
     * 预警记录查询
     * @param statisticsReqDTO 查询信息
     * @return EarlyWarnInfo
     */
    @PostMapping
    @ApiOperation(value = "预警记录查询")
    public PageResponse<EarlyWarnInfo> listEarlyWarnInfo(@Valid @RequestBody StatisticsReqDTO statisticsReqDTO){
        return PageResponse.of(earlyWarnService.listEarlyWarnInfo(statisticsReqDTO));
    }
}
