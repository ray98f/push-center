package com.zte.msg.pushcenter.pccore.controller;

import com.zte.msg.pushcenter.pccore.core.pusher.msg.AppMessage;
import com.zte.msg.pushcenter.pccore.core.pusher.msg.MailMessage;
import com.zte.msg.pushcenter.pccore.core.pusher.msg.SmsMessage;
import com.zte.msg.pushcenter.pccore.core.pusher.msg.WeChatMessage;
import com.zte.msg.pushcenter.pccore.dto.DataResponse;
import com.zte.msg.pushcenter.pccore.dto.req.AppMessageReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.MailMessageReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.SmsMessageReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.WeChatMessageReqDTO;
import com.zte.msg.pushcenter.pccore.enums.ErrorCode;
import com.zte.msg.pushcenter.pccore.exception.CommonException;
import com.zte.msg.pushcenter.pccore.service.PushCenterService;
import com.zte.msg.pushcenter.pccore.utils.Constants;
import com.zte.msg.pushcenter.pccore.utils.SignUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
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
@RequestMapping("/api/v1/open/push")
@Api(tags = "OpenApi消息推送")
@Validated
public class OpenPushCenterController {

    public static final String NULL = null;

    @Value("${sign.verify}")
    private Boolean signVerify;

    @Resource
    private PushCenterService pushCenterService;

    @PostMapping(value = "/sms")
    @ApiOperation(value = "短信推送")
    public <T> DataResponse<T> pushSms(@Valid @RequestBody SmsMessageReqDTO reqDTO) {
        if (signVerify){
            String sign = reqDTO.getSign();
            reqDTO.setSign(NULL);
            SignUtils.verify(reqDTO, reqDTO.getAppId(), reqDTO.getRequestTime(), sign);
        }
        if (reqDTO.getIsCallBack() && Objects.isNull(reqDTO.getCallBackUrl())) {
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        SmsMessage smsMessage = new SmsMessage().build(reqDTO);
        pushCenterService.pushSms(smsMessage);
        return DataResponse.success();
    }

    @PostMapping(value = "/mail")
    @ApiOperation(value = "邮件推送")
    public <T> DataResponse<T> pushMail(@Valid @RequestBody MailMessageReqDTO reqDTO) {
        if (signVerify){
            String sign = reqDTO.getSign();
            reqDTO.setSign(NULL);
            SignUtils.verify(reqDTO, reqDTO.getAppId(), reqDTO.getRequestTime(), sign);
        }
        MailMessage mailMessage = new MailMessage().build(reqDTO);
        pushCenterService.pushMail(mailMessage);
        return DataResponse.success();
    }

    @PostMapping(value = "/wechat")
    @ApiOperation(value = "公众号消息推送")
    public <T> DataResponse<T> pushWeChat(@Valid @RequestBody WeChatMessageReqDTO reqDTO) {
        if (signVerify){
            String sign = reqDTO.getSign();
            reqDTO.setSign(NULL);
            SignUtils.verify(reqDTO, reqDTO.getAppId(), reqDTO.getRequestTime(), sign);
        }
        WeChatMessage weChatMessage = new WeChatMessage().build(reqDTO);
        pushCenterService.pushWechat(weChatMessage);
        return DataResponse.success();
    }

    @PostMapping(value = "/app")
    @ApiOperation(value = "APP消息推送")
    public <T> DataResponse<T> pushApp(@Valid @RequestBody AppMessageReqDTO reqDTO) {
        if (signVerify){
            String sign = reqDTO.getSign();
            reqDTO.setSign(NULL);
            SignUtils.verify(reqDTO, reqDTO.getAppId(), reqDTO.getRequestTime(), sign);
        }
        AppMessage appMessage = new AppMessage().build(reqDTO);
        pushCenterService.pushApp(appMessage);
        return DataResponse.success();
    }
}