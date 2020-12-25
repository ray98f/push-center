package com.zte.msg.pushcenter.controller;

import com.zte.msg.pushcenter.dto.DataResponse;
import com.zte.msg.pushcenter.dto.req.ConfigReqDTO;
import com.zte.msg.pushcenter.dto.req.ScriptReqDTO;
import com.zte.msg.pushcenter.dto.req.SmsConfigReqDTO;
import com.zte.msg.pushcenter.dto.req.SmsTemplateReqDTO;
import com.zte.msg.pushcenter.dto.res.ConfigResDTO;
import com.zte.msg.pushcenter.dto.res.SmsConfigResDTO;
import com.zte.msg.pushcenter.dto.res.SmsTemplateResDTO;
import com.zte.msg.pushcenter.service.ConfigService;
import com.zte.msg.pushcenter.service.ScriptService;
import com.zte.msg.pushcenter.service.SmsConfigService;
import com.zte.msg.pushcenter.service.SmsTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    private SmsConfigService smsConfigService;

    @Resource
    private SmsTemplateService smsTemplateService;

    @Resource
    private ConfigService configService;

    @Resource
    private ScriptService scriptService;

    @PostMapping
    @ApiOperation(value = "添加配置")
    public DataResponse<ConfigResDTO> addConfig(@RequestBody @Valid @ApiParam(value = "添加一条基本配置") ConfigReqDTO config) {

        return DataResponse.of(configService.addConfig(config));
    }

    @PostMapping(value = "/sms")
    @ApiOperation(value = "指定供应商下面的短信接口相关配置")
    public DataResponse<SmsConfigResDTO> addSmsConfig(@RequestBody @Valid SmsConfigReqDTO reqDTO) {
        return DataResponse.of(smsConfigService.addSmsConfig(reqDTO));
    }

    @PostMapping(value = "/sms/template")
    @ApiOperation(value = "添加短信模版配置接口，来自于第三方服务的模版配置")
    public DataResponse<SmsTemplateResDTO> addSmsTemplate(@RequestBody @Valid @ApiParam(value = "对应的供应商模版配置")
                                                                  SmsTemplateReqDTO templateConfig) {

        return DataResponse.of(smsTemplateService.addSmsTemplate(templateConfig));
    }

    @PostMapping(value = "/script")
    @ApiOperation(value = "添加脚本")
    public <T> DataResponse<T> addScript(@RequestBody ScriptReqDTO script) {

        scriptService.addScript(script);
        return DataResponse.success();
    }

}
