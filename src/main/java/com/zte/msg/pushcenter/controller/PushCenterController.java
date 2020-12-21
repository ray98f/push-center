package com.zte.msg.pushcenter.controller;

import com.alibaba.fastjson.JSONObject;
import com.zte.msg.pushcenter.common.pusher.MailPusher;
import com.zte.msg.pushcenter.common.pusher.SmsPusher;
import com.zte.msg.pushcenter.common.pusher.WeChatPusher;
import com.zte.msg.pushcenter.dto.DataResponse;
import com.zte.msg.pushcenter.dto.req.MailMessageReqDTO;
import com.zte.msg.pushcenter.dto.req.SmsMessageReqDTO;
import com.zte.msg.pushcenter.dto.req.WeChatMessageReqDTO;
import com.zte.msg.pushcenter.msg.MailMessage;
import com.zte.msg.pushcenter.msg.SmsMessage;
import com.zte.msg.pushcenter.msg.WeChatMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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

    @Resource(name = "asyncPushExecutor")
    protected ThreadPoolTaskExecutor executor;

    @Resource
    private SmsPusher smsPusher;

    @Resource
    private MailPusher mailPusher;

    @Resource
    private WeChatPusher weChatPusher;

    @PostMapping(value = "/sms")
    @ApiOperation(value = "短信推送")
    public DataResponse<JSONObject> pushSms(@Valid SmsMessageReqDTO reqDTO) {

        SmsMessage smsMessage = new SmsMessage().build(reqDTO);
        smsPusher.submit(smsMessage);
        return DataResponse.success();
    }

    @PostMapping(value = "/mail")
    @ApiOperation(value = "邮件推送")
    public DataResponse<JSONObject> pushMail(@Valid MailMessageReqDTO reqDTO) {

        MailMessage mailMessage = new MailMessage().build(reqDTO);
         mailPusher.submit(mailMessage);
        return DataResponse.success();
    }

    @PostMapping(value = "/wechat")
    @ApiOperation(value = "公众号推送")
    public DataResponse<JSONObject> pushWeChat(@Valid WeChatMessageReqDTO reqDTO) {
        WeChatMessage weChatMessage = new WeChatMessage().build(reqDTO);
        weChatPusher.submit(weChatMessage);
        return DataResponse.success();

    }

    @GetMapping(value = "/wechat/auth")
    @ApiOperation(value = "微信公众号获取accessKey")
    public DataResponse<String> weChatAuth(@RequestParam(value = "wechat_app_id") @ApiParam(value = "微信公众号appId") String weChatAppId,
                                           @RequestParam(value = "app_secret") @ApiParam(value = "appSecret") String appSecret) {

        return null;
//        return DataResponse.of(weChatPusher.getAccessKey(weChatAppId, appSecret));
    }

    @GetMapping(value = "/sync/test")
    @ApiOperation(value = "测试")
    public DataResponse<String> test() {
        String s = "";
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "111111111111";
        }, executor);

        try {
            s = stringCompletableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return DataResponse.of(s);
    }


}

