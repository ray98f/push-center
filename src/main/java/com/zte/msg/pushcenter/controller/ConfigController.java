package com.zte.msg.pushcenter.controller;

import com.zte.msg.pushcenter.dto.DataResponse;
import com.zte.msg.pushcenter.dto.req.ProviderReqDTO;
import com.zte.msg.pushcenter.dto.req.SmsProviderConfigReqDTO;
import com.zte.msg.pushcenter.dto.req.SmsTemplateReqDTO;
import com.zte.msg.pushcenter.dto.res.ProviderResDTO;
import com.zte.msg.pushcenter.dto.res.SmsProviderConfigResDTO;
import com.zte.msg.pushcenter.dto.res.SmsTemplateResDTO;
import com.zte.msg.pushcenter.service.ProviderService;
import com.zte.msg.pushcenter.service.SmsProviderConfigService;
import com.zte.msg.pushcenter.service.SmsTemplateService;
import io.swagger.annotations.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/21 9:37
 */
@RestController
@RequestMapping("/api/v1/conf")
@Api(tags = "配置")
@Validated
public class ConfigController {

    @Resource
    private SmsProviderConfigService smsProviderConfigService;

    @Resource
    private SmsTemplateService smsTemplateService;

    @Resource
    private ProviderService providerService;

    @PostMapping(value = "/provider")
    @ApiOperation(value = "添加供应商配置")
    public DataResponse<ProviderResDTO> addProvider(@RequestBody @Valid @ApiParam(value = "短信供应商配置")
                                                            ProviderReqDTO provider) {

        return DataResponse.of(providerService.addProvider(provider));
    }

    @PostMapping(value = "/sms/provider/config")
    @ApiOperation(value = "指定供应商下面的短信接口相关配置")
    public DataResponse<SmsProviderConfigResDTO> addSmsProviderConfig(@RequestBody @Valid SmsProviderConfigReqDTO reqDTO) {
        return DataResponse.of(smsProviderConfigService.addSmsProviderConfig(reqDTO));
    }


    @PostMapping(value = "/sms/template")
    @ApiOperation(value = "添加短信模版配置接口")
    public DataResponse<SmsTemplateResDTO> addSmsTemplate(@RequestBody @Valid @ApiParam(value = "对应的供应商模版配置")
                                                                  SmsTemplateReqDTO templateConfig) {

        return DataResponse.of(smsTemplateService.addSmsTemplate(templateConfig));
    }


}
