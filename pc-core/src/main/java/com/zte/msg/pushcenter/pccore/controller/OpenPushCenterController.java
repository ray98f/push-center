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
import com.zte.msg.pushcenter.pccore.utils.Constants;
import com.zte.msg.pushcenter.pccore.utils.MapUtils;
import com.zte.msg.pushcenter.pccore.utils.SignUtils;
import com.zte.msg.pushcenter.pccore.validation.RegisterUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
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
        if (signVerify) {
            String sign = reqDTO.getSign();
            reqDTO.setSign(NULL);
            SignUtils.verify(MapUtils.objectToMap(reqDTO), reqDTO.getAppId(), reqDTO.getRequestTime(), sign);
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
        if (signVerify) {
            String sign = reqDTO.getSign();
            reqDTO.setSign(NULL);
            SignUtils.verify(MapUtils.objectToMap(reqDTO), reqDTO.getAppId(), reqDTO.getRequestTime(), sign);
        }
        MailMessage mailMessage = new MailMessage().build(reqDTO);
        pushCenterService.pushMail(mailMessage);
        return DataResponse.success();
    }

    @PostMapping(value = "/wechat")
    @ApiOperation(value = "公众号消息推送")
    public <T> DataResponse<T> pushWeChat(@Valid @RequestBody WeChatMessageReqDTO reqDTO) {
        if (signVerify) {
            String sign = reqDTO.getSign();
            reqDTO.setSign(NULL);
            SignUtils.verify(MapUtils.objectToMap(reqDTO), reqDTO.getAppId(), reqDTO.getRequestTime(), sign);
        }
        WeChatMessage weChatMessage = new WeChatMessage().build(reqDTO);
        pushCenterService.pushWechat(weChatMessage);
        return DataResponse.success();
    }

    @PostMapping(value = "/app")
    @ApiOperation(value = "APP消息推送")
    public <T> DataResponse<T> pushApp(@Valid @RequestBody AppMessageReqDTO reqDTO) {
        if (signVerify) {
            String sign = reqDTO.getSign();
            reqDTO.setSign(NULL);
            SignUtils.verify(MapUtils.objectToMap(reqDTO), reqDTO.getAppId(), reqDTO.getRequestTime(), sign);
        }
        AppMessage appMessage = new AppMessage().build(reqDTO);
        pushCenterService.pushApp(appMessage);
        return DataResponse.success();
    }

    @PostMapping(value = "/register")
    @ApiOperation(value = "注册用户ID")
    public <T> DataResponse<T> registerUser(@Validated(RegisterUser.class) @RequestBody RegisterReqDTO reqDTO) {

        pushCenterService.register(reqDTO);
        return DataResponse.success();
    }


    @GetMapping(value = "/cid")
    @ApiOperation(value = "获取用户ID")
    public DataResponse<String> getCid(@RequestParam String sysCode,
                                       @RequestParam String userId) {

        return DataResponse.of(pushCenterService.getCid(sysCode, userId));
    }

    @GetMapping(value = "/cids")
    @ApiOperation(value = "获取用户ID")
    public DataResponse<List<String>> getCids(@RequestParam String sysCode,
                                              @RequestParam List<String> userId) {

        return DataResponse.of(pushCenterService.getCids(sysCode, userId));
    }
}