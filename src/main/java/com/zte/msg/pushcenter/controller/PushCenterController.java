package com.zte.msg.pushcenter.controller;

import com.alibaba.fastjson.JSONObject;
import com.zte.msg.pushcenter.common.pusher.SmsPusher;
import com.zte.msg.pushcenter.dto.DataResponse;
import com.zte.msg.pushcenter.dto.req.SmsMessageReqDTO;
import com.zte.msg.pushcenter.msg.SmsMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/api/v1")
@Api(tags = "消息推送")
@Validated
public class PushCenterController {

    @Resource
    private SmsPusher smsPusher;

    @PostMapping(value = "/push/sms")
    @ApiOperation(value = "短信推送")
    public DataResponse<JSONObject> pushSms(@Valid SmsMessageReqDTO reqDTO) {

        SmsMessage smsMessage = new SmsMessage().build(reqDTO);
        CompletableFuture<JSONObject> push = smsPusher.push(smsMessage);
        JSONObject jsonObject = null;
        try {
            jsonObject = push.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return DataResponse.of(jsonObject);
    }

}

