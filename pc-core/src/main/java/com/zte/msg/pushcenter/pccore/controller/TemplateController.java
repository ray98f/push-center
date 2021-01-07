package com.zte.msg.pushcenter.pccore.controller;

import com.zte.msg.pushcenter.pccore.dto.DataResponse;
import com.zte.msg.pushcenter.pccore.dto.PageReqDTO;
import com.zte.msg.pushcenter.pccore.dto.PageResponse;
import com.zte.msg.pushcenter.pccore.dto.req.SmsTemplateRelateProviderReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.SmsTemplateRelateProviderUpdateReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.SmsTemplateReqDTO;
import com.zte.msg.pushcenter.pccore.dto.res.SmsTemplateDetailResDTO;
import com.zte.msg.pushcenter.pccore.service.TemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
    @ApiOperation(value = "【短信模版】- 添加")
    public <T> DataResponse<T> addSmsTemplate(@RequestBody @Valid SmsTemplateReqDTO templateConfig) {
        templateService.addSmsTemplate(templateConfig);
        return DataResponse.success();
    }

    @GetMapping(value = "/sms/page")
    @ApiOperation(value = "【短信模版】- 分页查询")
    public PageResponse<SmsTemplateDetailResDTO> getSmsTemplate(@RequestParam(required = false)
                                                                @ApiParam(value = "模板内容模糊查询字段") String content,
                                                                @RequestParam(required = false)
                                                                @ApiParam(value = "模版id") Long templateId,
                                                                @RequestParam(required = false)
                                                                @ApiParam(value = "模版启用状态") Integer status,
                                                                @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(templateService.getTemplateByPage(content, templateId, status, pageReqDTO));
    }

    @PutMapping(value = "/sms/{templateId}")
    @ApiOperation(value = "【短信模版】- 修改短信模版")
    public <T> DataResponse<T> updateSmsTemplate(@PathVariable Long templateId,
                                                 @RequestBody @Valid SmsTemplateReqDTO reqDTO) {

        templateService.updateSmsTemplate(templateId, reqDTO);
        return DataResponse.success();
    }

    @DeleteMapping(value = "/sms")
    @ApiOperation(value = "【短信模版】- 删除短信模版")
    public <T> DataResponse<T> removeSmsTemplate(@RequestParam @ApiParam(value = "模版id数组") Long[] templateIds) {

        templateService.deleteSmsTemplate(templateIds);
        return DataResponse.success();
    }

    @PostMapping(value = "/sms/relation/{templateId}")
    @ApiOperation(value = "【短信模版】- 新增消息平台模版关联配置")
    public <T> DataResponse<T> addProviderSmsTemplateRelate(@PathVariable Long templateId,
                                                            @RequestBody SmsTemplateRelateProviderReqDTO reqDTO) {
        templateService.addProviderSmsTemplateRelate(templateId, reqDTO);
        return DataResponse.success();
    }

    @PutMapping(value = "/sms/relation/{templateId}")
    @ApiOperation(value = "【短信模板】- 修改消息平台模版关联配置")
    public <T> DataResponse<T> updateProviderSmsTemplateRelate(@PathVariable @ApiParam(value = "短信模版id") Long templateId,
                                                               @RequestBody SmsTemplateRelateProviderUpdateReqDTO reqDTO) {
        templateService.updateProviderSmsTemplateRelate(templateId, reqDTO);
        return DataResponse.success();
    }

    @DeleteMapping(value = "/sms/relation/{templateId}")
    @ApiOperation(value = "【短信模版】- 删除消息平台模版配置的关联模版")
    public <T> DataResponse<T> deleteProviderSmsTemplateRelate(@PathVariable Long templateId,
                                                               @RequestParam @NotNull(message = "32000006")
                                                               @ApiParam(value = "模版关联关系id数组") Long[] relationIds) {
        templateService.deleteProviderSmsTemplateRelate(templateId, relationIds);
        return DataResponse.success();
    }
}
