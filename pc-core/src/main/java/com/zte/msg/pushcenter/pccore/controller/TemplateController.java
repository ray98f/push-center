package com.zte.msg.pushcenter.pccore.controller;

import com.zte.msg.pushcenter.pccore.dto.DataResponse;
import com.zte.msg.pushcenter.pccore.dto.PageReqDTO;
import com.zte.msg.pushcenter.pccore.dto.PageResponse;
import com.zte.msg.pushcenter.pccore.dto.req.SmsTemplateReqDTO;
import com.zte.msg.pushcenter.pccore.dto.res.SmsTemplateDetailResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.SmsTemplateResDTO;
import com.zte.msg.pushcenter.pccore.service.TemplateService;
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
 * @date 2021/1/4 9:38
 */
@RestController
@RequestMapping("/api/v1/template")
@Api(tags = "模版管理")
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

    @GetMapping(value = "/sms")
    @ApiOperation(value = "分页查询短息模版")
    public PageResponse<SmsTemplateResDTO> getSmsTemplate(@RequestParam(value = "模版内容的模糊查询字段", required = false) String example,
                                                          @Valid PageReqDTO pageReqDTO) {
        // TODO: 2021/1/4
        return null;
    }

    @GetMapping(value = "/sms/{id}")
    @ApiOperation(value = "根据id查询模板详情")
    public DataResponse<SmsTemplateDetailResDTO> getSmsTemplateById(@PathVariable Integer id) {
        return null;
    }

    @DeleteMapping(value = "/sms/{id}")
    @ApiOperation(value = "根据id删除模版详情")
    public <T> DataResponse<T> removeTemplateById(@PathVariable Integer id) {

        return DataResponse.success();
    }

    @PutMapping(value = "/sms/{id}")
    @ApiOperation(value = "更新模版")
    public <T> DataResponse<T> updateTemplate(@PathVariable Integer id,
                                              @RequestBody @Valid SmsTemplateReqDTO smsTemplateReqDTO) {
        return DataResponse.success();
    }

}
