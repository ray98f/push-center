package com.zte.msg.pushcenter.pccore.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zte.msg.pushcenter.pccore.dto.DataResponse;
import com.zte.msg.pushcenter.pccore.dto.PageReqDTO;
import com.zte.msg.pushcenter.pccore.dto.PageResponse;
import com.zte.msg.pushcenter.pccore.dto.req.SmsConfigReqDTO;
import com.zte.msg.pushcenter.pccore.dto.res.SmsConfigDetailResDTO;
import com.zte.msg.pushcenter.pccore.entity.Sms;
import com.zte.msg.pushcenter.pccore.service.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/28 14:49
 */
@RestController
@RequestMapping("/api/v1/sms")
@Api(tags = "短信")
@Validated
public class SmsController {

    @Resource
    private SmsService smsService;

    @PostMapping(value = "/conf")
    @ApiOperation(value = "添加短信配置")
    public DataResponse<SmsConfigDetailResDTO> addSmsConfig(@RequestBody @Valid SmsConfigReqDTO reqDTO) {
        reqDTO.encrypt();
        SmsConfigDetailResDTO resDTO = smsService.addSmsConfig(reqDTO);
        resDTO.decrypt();
        return DataResponse.of(resDTO);
    }

    @GetMapping(value = "/conf/{id}")
    @ApiOperation(value = "根据id查询短信配置详情")
    public DataResponse<SmsConfigDetailResDTO> getSmsConfig(@PathVariable Long id) {
        SmsConfigDetailResDTO smsConfig = smsService.getSmsConfig(id);
        smsConfig.decrypt();
        return DataResponse.of(smsConfig);
    }

    @PutMapping(value = "/conf/{id}")
    @ApiOperation(value = "修改短信配置")
    public <T> DataResponse<T> updateSmsConfig(@PathVariable Long id,
                                               @RequestBody SmsConfigReqDTO reqDTO) {
        reqDTO.encrypt();
        smsService.updateSmsConfig(id, reqDTO);
        return DataResponse.success();
    }

    @DeleteMapping(value = "/conf/{id}")
    @ApiOperation(value = "根据id删除短信配置")
    public <T> DataResponse<T> deleteSmsConfig(@PathVariable Long id) {
        smsService.deleteSmsConfig(id);
        return DataResponse.success();
    }

    @GetMapping(value = "/conf/page")
    @ApiOperation(value = "分页查询")
    public PageResponse<SmsConfigDetailResDTO> getSmsConfigs(@Valid PageReqDTO page) {
        Page<SmsConfigDetailResDTO> smsConfigs = smsService.getSmsConfigs(page);
        smsConfigs.getRecords().forEach(SmsConfigDetailResDTO::decrypt);
        return PageResponse.of(smsConfigs);
    }

    @GetMapping("/history")
    @ApiOperation(value = "查询短信发送历史")
    public DataResponse<List<Sms>> listHistorySms(){
        return DataResponse.of(smsService.listHistorySms());
    }

}
