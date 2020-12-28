package com.zte.msg.pushcenter.controller;

import com.zte.msg.pushcenter.dto.DataResponse;
import com.zte.msg.pushcenter.dto.PageReqDTO;
import com.zte.msg.pushcenter.dto.PageResponse;
import com.zte.msg.pushcenter.dto.req.SmsConfigReqDTO;
import com.zte.msg.pushcenter.dto.req.SmsTemplateReqDTO;
import com.zte.msg.pushcenter.dto.res.SmsConfigResDTO;
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
    public DataResponse<SmsConfigResDTO> addSmsConfig(@RequestBody @Valid SmsConfigReqDTO reqDTO) {
        return DataResponse.of(smsConfigService.addSmsConfig(reqDTO));
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "修改短信配置")
    public void updateSmsConfig(@PathVariable Long id,
                                @RequestBody SmsConfigReqDTO reqDTO) {
        smsConfigService.updateSmsConfig(id, reqDTO);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "根据id删除短信配置")
    public void deleteSmsConfig(@PathVariable Long id) {
        smsConfigService.deleteSmsConfig(id);
    }

    @GetMapping(value = "/page")
    @ApiOperation(value = "分页查询")
    public PageResponse<SmsConfigResDTO> getSmsConfigs(@Valid PageReqDTO page) {
        return PageResponse.of(smsConfigService.getSmsConfigs(page));
    }

    @PostMapping(value = "/template")
    @ApiOperation(value = "添加短信模版配置接口，来自于第三方服务的模版配置")
    public DataResponse<SmsTemplateResDTO> addSmsTemplate(@RequestBody @Valid @ApiParam(value = "对应的供应商模版配置")
                                                                  SmsTemplateReqDTO templateConfig) {

        return DataResponse.of(smsTemplateService.addSmsTemplate(templateConfig));
    }

}
