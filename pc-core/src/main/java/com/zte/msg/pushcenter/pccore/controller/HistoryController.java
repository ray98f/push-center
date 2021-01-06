package com.zte.msg.pushcenter.pccore.controller;

import com.github.pagehelper.PageInfo;
import com.zte.msg.pushcenter.pccore.dto.DataResponse;
import com.zte.msg.pushcenter.pccore.dto.req.MailHistoryReqDTO;
import com.zte.msg.pushcenter.pccore.entity.MailInfo;
import com.zte.msg.pushcenter.pccore.entity.Sms;
import com.zte.msg.pushcenter.pccore.service.HistoryService;
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
    public DataResponse<List<Sms>> listHistorySms(){
        return DataResponse.of(historyService.listHistorySms());
    }

    @PostMapping("/mail")
    @ApiOperation(value = "查询邮件发送历史")
    public DataResponse<PageInfo<MailInfo>> listHistoryMail(@Valid @RequestBody MailHistoryReqDTO mailHistoryReqDTO){
        return DataResponse.of(historyService.listHistoryMail(mailHistoryReqDTO));
    }


}
