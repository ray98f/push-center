package com.zte.msg.pushcenter.pccore.controller;

import com.alibaba.fastjson.JSONObject;
import com.zte.msg.pushcenter.pccore.core.pusher.msg.MailMessage;
import com.zte.msg.pushcenter.pccore.core.pusher.msg.SmsMessage;
import com.zte.msg.pushcenter.pccore.dto.DataResponse;
import com.zte.msg.pushcenter.pccore.dto.req.MailMessageReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.SmsMessageReqDTO;
import com.zte.msg.pushcenter.pccore.enums.ErrorCode;
import com.zte.msg.pushcenter.pccore.enums.PushMethods;
import com.zte.msg.pushcenter.pccore.exception.CommonException;
import com.zte.msg.pushcenter.pccore.service.PushCenterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Objects;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/10 13:57
 */
@RestController
@RequestMapping("/api/open/v1/push")
@Api(tags = "消息推送")
@Validated
public class PushCenterController {

    @Resource
    private PushCenterService pushCenterService;


    @PostMapping(value = "/sms")
    @ApiOperation(value = "短信推送")
    public DataResponse<JSONObject> pushSms(@Valid @RequestBody SmsMessageReqDTO reqDTO) {
//        SignUtils.verify(reqDTO, reqDTO.getAppId(), reqDTO.getRequestTime(), reqDTO.getSign());
        if (reqDTO.getIsCallBack() && Objects.isNull(reqDTO.getCallBackUrl())) {
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        SmsMessage smsMessage = new SmsMessage().build(reqDTO);
        smsMessage.setPushMethod(PushMethods.SMS);
        pushCenterService.pushSms(smsMessage);
        return DataResponse.success();
    }

    @PostMapping(value = "/mail")
    @ApiOperation(value = "邮件推送")
    public DataResponse<JSONObject> pushMail(@Valid @RequestBody MailMessageReqDTO reqDTO) {
//        SignUtils.verify(reqDTO, reqDTO.getAppId(), reqDTO.getRequestTime(), reqDTO.getSign());
        MailMessage mailMessage = new MailMessage().build(reqDTO);
        mailMessage.setPushMethod(PushMethods.MAIL);
        pushCenterService.pushMail(mailMessage);
        return DataResponse.success();
    }

}