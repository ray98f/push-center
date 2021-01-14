package com.zte.msg.pushcenter.pccore.controller;

import com.zte.msg.pushcenter.pccore.dto.PageResponse;
import com.zte.msg.pushcenter.pccore.dto.req.ApplicationHistoryReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.MailHistoryReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.SmsHistoryReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.WechatHistoryReqDTO;
import com.zte.msg.pushcenter.pccore.entity.ApplicationInfo;
import com.zte.msg.pushcenter.pccore.entity.MailInfo;
import com.zte.msg.pushcenter.pccore.entity.SmsInfo;
import com.zte.msg.pushcenter.pccore.entity.WeChatInfo;
import com.zte.msg.pushcenter.pccore.service.HistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * description:
 *
 * @author frp
 * @version 1.0
 * @date 2021/1/6 9:25
 */
@RestController
@RequestMapping("/api/v1/history")
@Api(tags = "历史查询")
@Validated
public class HistoryController {

    @Resource
    private HistoryService historyService;

    @PostMapping("/sms")
    @ApiOperation(value = "查询短信发送历史")
    public PageResponse<SmsInfo> listHistorySms(@Valid @RequestBody SmsHistoryReqDTO smsHistoryReqDTO){
        return PageResponse.of(historyService.listHistorySms(smsHistoryReqDTO));
    }

    @PostMapping("/mail")
    @ApiOperation(value = "查询邮件发送历史")
    public PageResponse<MailInfo> listHistoryMail(@Valid @RequestBody MailHistoryReqDTO mailHistoryReqDTO){
        return PageResponse.of(historyService.listHistoryMail(mailHistoryReqDTO));
    }

    @PostMapping("/wechat")
    @ApiOperation(value = "查询微信公众号发送历史")
    public PageResponse<WeChatInfo> listHistoryWechat(@Valid @RequestBody WechatHistoryReqDTO wechatHistoryReqDTO){
        return PageResponse.of(historyService.listHistoryWechat(wechatHistoryReqDTO));
    }

    @PostMapping("/application")
    @ApiOperation(value = "查询App应用发送历史")
    public PageResponse<ApplicationInfo> listHistoryApplication(@Valid @RequestBody ApplicationHistoryReqDTO applicationHistoryReqDTO){
        return PageResponse.of(historyService.listHistoryApplication(applicationHistoryReqDTO));
    }
}
