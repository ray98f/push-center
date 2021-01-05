package com.zte.msg.pushcenter.pccore.controller;

import com.zte.msg.pushcenter.pccore.dto.DataResponse;
import com.zte.msg.pushcenter.pccore.entity.Sms;
import com.zte.msg.pushcenter.pccore.service.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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

    @GetMapping("/history")
    @ApiOperation(value = "查询短信发送历史")
    public DataResponse<List<Sms>> listHistorySms(){
        return DataResponse.of(smsService.listHistorySms());
    }

}
