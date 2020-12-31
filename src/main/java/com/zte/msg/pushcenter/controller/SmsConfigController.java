package com.zte.msg.pushcenter.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zte.msg.pushcenter.dto.DataResponse;
import com.zte.msg.pushcenter.dto.PageReqDTO;
import com.zte.msg.pushcenter.dto.PageResponse;
import com.zte.msg.pushcenter.dto.req.SmsConfigReqDTO;
import com.zte.msg.pushcenter.dto.req.SmsTemplateReqDTO;
import com.zte.msg.pushcenter.dto.res.SmsConfigDetailResDTO;
import com.zte.msg.pushcenter.dto.res.SmsTemplateResDTO;
import com.zte.msg.pushcenter.service.SmsConfigService;
import com.zte.msg.pushcenter.service.SmsTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/28 14:49
 */
@RestController
@RequestMapping("/api/v1/sms")
@Api(tags = "短信配置")
@Validated
public class SmsConfigController {

    @Resource
    private SmsConfigService smsConfigService;

    @Resource
    private SmsTemplateService smsTemplateService;

    @PostMapping
    @ApiOperation(value = "添加短信配置")
    public DataResponse<SmsConfigDetailResDTO> addSmsConfig(@RequestBody @Valid SmsConfigReqDTO reqDTO) {
        reqDTO.encrypt();
        SmsConfigDetailResDTO resDTO = smsConfigService.addSmsConfig(reqDTO);
        resDTO.decrypt();
        return DataResponse.of(resDTO);
    }

    @GetMapping(value = "{id}")
    @ApiOperation(value = "根据id查询短信配置详情")
    public DataResponse<SmsConfigDetailResDTO> getSmsConfig(@PathVariable Long id) {
        SmsConfigDetailResDTO smsConfig = smsConfigService.getSmsConfig(id);
        smsConfig.decrypt();
        return DataResponse.of(smsConfig);
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "修改短信配置")
    public <T> DataResponse<T> updateSmsConfig(@PathVariable Long id,
                                               @RequestBody SmsConfigReqDTO reqDTO) {
        reqDTO.encrypt();
        smsConfigService.updateSmsConfig(id, reqDTO);
        return DataResponse.success();
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "根据id删除短信配置")
    public <T> DataResponse<T> deleteSmsConfig(@PathVariable Long id) {
        smsConfigService.deleteSmsConfig(id);
        return DataResponse.success();
    }

    @GetMapping(value = "/page")
    @ApiOperation(value = "分页查询")
    public PageResponse<SmsConfigDetailResDTO> getSmsConfigs(@Valid PageReqDTO page) {
        Page<SmsConfigDetailResDTO> smsConfigs = smsConfigService.getSmsConfigs(page);
        smsConfigs.getRecords().forEach(SmsConfigDetailResDTO::decrypt);
        return PageResponse.of(smsConfigs);
    }

    @PostMapping(value = "/template")
    @ApiOperation(value = "添加短信模版配置接口，来自于第三方服务的模版配置")
    public DataResponse<SmsTemplateResDTO> addSmsTemplate(@RequestBody @Valid @ApiParam(value = "对应的供应商模版配置")
                                                                  SmsTemplateReqDTO templateConfig) {
        return DataResponse.of(smsTemplateService.addSmsTemplate(templateConfig));
    }

}
