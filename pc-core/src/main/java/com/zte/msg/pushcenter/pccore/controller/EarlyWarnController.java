package com.zte.msg.pushcenter.pccore.controller;

import com.zte.msg.pushcenter.pccore.dto.DataResponse;
import com.zte.msg.pushcenter.pccore.dto.PageReqDTO;
import com.zte.msg.pushcenter.pccore.dto.PageResponse;
import com.zte.msg.pushcenter.pccore.entity.EarlyWarnConfig;
import com.zte.msg.pushcenter.pccore.entity.EarlyWarnInfo;
import com.zte.msg.pushcenter.pccore.service.EarlyWarnInfoService;
import com.zte.msg.pushcenter.pccore.service.EarlyWarnService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.sql.Timestamp;

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

    @Resource
    private EarlyWarnInfoService earlyWarnInfoService;

    /**
     * 查看预警配置
     *
     * @return
     */
    @GetMapping("/config")
    @ApiOperation(value = "查看预警配置")
    public DataResponse<EarlyWarnConfig> selectEarlyWarnConfig() {
        return DataResponse.of(earlyWarnService.selectEarlyWarnConfig());
    }

    /**
     * 新增预警配置
     *
     * @param earlyWarnConfig 预警配置信息
     * @return <T>
     */
    @PutMapping("/config")
    @ApiOperation(value = "编辑预警配置")
    public <T> DataResponse<T> editEarlyWarnConfig(@Valid @RequestBody EarlyWarnConfig earlyWarnConfig) {
        earlyWarnService.saveOrUpdateEarlyWarnConfig(earlyWarnConfig);
        return DataResponse.success();
    }

    /**
     * 预警记录查询
     *
     * @param pageReqDTO
     * @return
     */
    @GetMapping
    @ApiOperation(value = "预警记录查询")
    public PageResponse<EarlyWarnInfo> listEarlyWarnInfo(@Valid PageReqDTO pageReqDTO,
                                                         @RequestParam(required = false) Timestamp startTime,
                                                         @RequestParam(required = false) Timestamp endTime) {
        return PageResponse.of(earlyWarnInfoService.getWarnInfoByPage(pageReqDTO, startTime, endTime));
    }
}
