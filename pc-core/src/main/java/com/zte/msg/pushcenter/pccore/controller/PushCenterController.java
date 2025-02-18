package com.zte.msg.pushcenter.pccore.controller;

import com.zte.msg.pushcenter.pccore.core.pusher.msg.AppMessage;
import com.zte.msg.pushcenter.pccore.core.pusher.msg.MailMessage;
import com.zte.msg.pushcenter.pccore.core.pusher.msg.SmsMessage;
import com.zte.msg.pushcenter.pccore.core.pusher.msg.WeChatMessage;
import com.zte.msg.pushcenter.pccore.dto.DataResponse;
import com.zte.msg.pushcenter.pccore.dto.req.*;
import com.zte.msg.pushcenter.pccore.enums.ErrorCode;
import com.zte.msg.pushcenter.pccore.exception.CommonException;
import com.zte.msg.pushcenter.pccore.service.PushCenterService;
import com.zte.msg.pushcenter.pccore.utils.PatternUtils;
import com.zte.msg.pushcenter.pccore.annotation.PermissionCheck;
import com.zte.msg.pushcenter.pccore.validation.RegisterUser;
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
@RequestMapping("/api/v1/push")
@Api(tags = "消息推送")
@Validated
public class PushCenterController {

    @Resource
    private PushCenterService pushCenterService;

    @PostMapping(value = "/sms")
    @PermissionCheck(permissionName = "sms:send")
    @ApiOperation(value = "短信推送")
    public <T> DataResponse<T> pushSms(@Valid @RequestBody SmsMessageReqDTO reqDTO) {
        if (reqDTO.getIsCallBack() && Objects.isNull(reqDTO.getCallBackUrl())) {
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        if (!PatternUtils.validPhoneNums(reqDTO.getPhoneNum())) {
            throw new CommonException(ErrorCode.PHONE_NUM_ERROR);
        }
        SmsMessage smsMessage = new SmsMessage().build(reqDTO);
        pushCenterService.pushSms(smsMessage);
        return DataResponse.success();
    }

    @PostMapping(value = "/mail")
    @ApiOperation(value = "邮件推送")
    @PermissionCheck(permissionName = "mail:send")
    public <T> DataResponse<T> pushMail(@Valid @RequestBody MailMessageReqDTO reqDTO) {
        MailMessage mailMessage = new MailMessage().build(reqDTO);
        if (!PatternUtils.validEmails(reqDTO.getCc()) || !PatternUtils.validEmails(reqDTO.getTo())) {
            throw new CommonException(ErrorCode.MAIL_ADDRESS_INVALID);
        }
        pushCenterService.pushMail(mailMessage);
        return DataResponse.success();
    }

    @PostMapping(value = "/wechat")
    @PermissionCheck(permissionName = "wechat:send")
    @ApiOperation(value = "公众号消息推送")
    public <T> DataResponse<T> pushWeChat(@Valid @RequestBody WeChatMessageReqDTO reqDTO) {
        WeChatMessage weChatMessage = new WeChatMessage().build(reqDTO);
        pushCenterService.pushWechat(weChatMessage);
        return DataResponse.success();
    }

    @PostMapping(value = "/app")
    @PermissionCheck(permissionName = "app:send")
    @ApiOperation(value = "APP消息推送")
    public <T> DataResponse<T> pushApp(@Valid @RequestBody AppMessageReqDTO reqDTO) {
        AppMessage appMessage = new AppMessage().build(reqDTO);
        pushCenterService.pushApp(appMessage);
        return DataResponse.success();
    }


}