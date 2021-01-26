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
import com.zte.msg.pushcenter.pccore.utils.PermissionCheck;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

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

    /**
     * 分页查询短信发送历史
     *
     * @param smsHistoryReqDTO 短信历史信息
     * @return PageInfo<SmsInfo>
     */
    @PostMapping("/sms")
    @ApiOperation(value = "分页查询短信发送历史")
    @PermissionCheck(permissionName = "sms:info:list")
    public PageResponse<SmsInfo> listHistorySms(@Valid @RequestBody SmsHistoryReqDTO smsHistoryReqDTO) {
        return PageResponse.of(historyService.listHistorySms(smsHistoryReqDTO));
    }

    /**
     * 分页查询邮件发送历史
     *
     * @param mailHistoryReqDTO 邮件历史信息
     * @return PageInfo<MailInfo>
     */
    @PostMapping("/mail")
    @ApiOperation(value = "分页查询邮件发送历史")
    @PermissionCheck(permissionName = "sms:info:list")
    public PageResponse<MailInfo> listHistoryMail(@Valid @RequestBody MailHistoryReqDTO mailHistoryReqDTO) {
        return PageResponse.of(historyService.listHistoryMail(mailHistoryReqDTO));
    }

    /**
     * 分页查询微信公众号发送历史
     *
     * @param wechatHistoryReqDTO 公众号历史信息
     * @return PageInfo<WeChatInfo>
     */
    @PostMapping("/wechat")
    @ApiOperation(value = "分页查询微信公众号发送历史")
    @PermissionCheck(permissionName = "wechat:info:list")
    public PageResponse<WeChatInfo> listHistoryWechat(@Valid @RequestBody WechatHistoryReqDTO wechatHistoryReqDTO) {
        return PageResponse.of(historyService.listHistoryWechat(wechatHistoryReqDTO));
    }

    /**
     * 分页查询App应用发送历史
     *
     * @param applicationHistoryReqDTO app历史信息
     * @return PageInfo<ApplicationInfo>
     */
    @PostMapping("/application")
    @ApiOperation(value = "分页查询App应用发送历史")
    @PermissionCheck(permissionName = "app:info:list")
    public PageResponse<ApplicationInfo> listHistoryApplication(@Valid @RequestBody ApplicationHistoryReqDTO applicationHistoryReqDTO) {
        return PageResponse.of(historyService.listHistoryApplication(applicationHistoryReqDTO));
    }
}
