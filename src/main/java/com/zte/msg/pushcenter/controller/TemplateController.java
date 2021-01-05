package com.zte.msg.pushcenter.controller;

import com.zte.msg.pushcenter.dto.DataResponse;
import com.zte.msg.pushcenter.dto.req.SmsTemplateReqDTO;
import com.zte.msg.pushcenter.service.TemplateService;
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
 * @date 2021/1/4 9:38
 */
@RestController
@RequestMapping("/api/v1/template")
@Api(tags = "短信配置")
@Validated
public class TemplateController {

    @Resource
    private TemplateService templateService;

    @PostMapping(value = "/sms")
    @ApiOperation(value = "添加短信模版配置接口，来自于第三方服务的模版配置")
    public <T> DataResponse<T> addSmsTemplate(@RequestBody @Valid @ApiParam(value = "对应的供应商模版配置")
                                                      SmsTemplateReqDTO templateConfig) {
        templateService.addSmsTemplate(templateConfig);
        return DataResponse.success();
    }

}
