package com.zte.msg.pushcenter.pccore.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zte.msg.pushcenter.pccore.dto.DataResponse;
import com.zte.msg.pushcenter.pccore.dto.PageReqDTO;
import com.zte.msg.pushcenter.pccore.dto.PageResponse;
import com.zte.msg.pushcenter.pccore.dto.req.SmsConfigReqDTO;
import com.zte.msg.pushcenter.pccore.dto.res.SmsConfigDetailResDTO;
import com.zte.msg.pushcenter.pccore.service.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/5 10:34
 */
@RestController
@RequestMapping("/api/v1/config")
@Api(tags = "配置管理")
@Validated
public class ConfigController {

    @Resource
    private SmsService smsService;

    @PostMapping(value = "/sms")
    @ApiOperation(value = "短信配置-添加")
    public DataResponse<SmsConfigDetailResDTO> addSmsConfig(@RequestBody @Valid SmsConfigReqDTO reqDTO) {
        reqDTO.encrypt();
        SmsConfigDetailResDTO resDTO = smsService.addSmsConfig(reqDTO);
        resDTO.decrypt();
        return DataResponse.of(resDTO);
    }

    @GetMapping(value = "/sms/{id}")
    @ApiOperation(value = "短信配置-根据id查询详情")
    public DataResponse<SmsConfigDetailResDTO> getSmsConfig(@PathVariable Long id) {
        SmsConfigDetailResDTO smsConfig = smsService.getSmsConfig(id);
        smsConfig.decrypt();
        return DataResponse.of(smsConfig);
    }

    @PutMapping(value = "/sms/{id}")
    @ApiOperation(value = "短信配置-修改")
    public <T> DataResponse<T> updateSmsConfig(@PathVariable Long id,
                                               @RequestBody SmsConfigReqDTO reqDTO) {
        reqDTO.encrypt();
        smsService.updateSmsConfig(id, reqDTO);
        return DataResponse.success();
    }

    @DeleteMapping(value = "/sms/{id}")
    @ApiOperation(value = "短信配置-根据id删除")
    public <T> DataResponse<T> deleteSmsConfig(@PathVariable Long id) {
        smsService.deleteSmsConfig(id);
        return DataResponse.success();
    }

    @GetMapping(value = "/sms/page")
    @ApiOperation(value = "短信配置-分页查询")
    public PageResponse<SmsConfigDetailResDTO> getSmsConfigs(@Valid PageReqDTO page) {
        Page<SmsConfigDetailResDTO> smsConfigs = smsService.getSmsConfigs(page);
        smsConfigs.getRecords().forEach(SmsConfigDetailResDTO::decrypt);
        return PageResponse.of(smsConfigs);
    }
}
