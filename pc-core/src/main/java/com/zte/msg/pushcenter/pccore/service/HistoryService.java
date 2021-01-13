package com.zte.msg.pushcenter.pccore.service;

import com.github.pagehelper.PageInfo;
import com.zte.msg.pushcenter.pccore.dto.req.ApplicationHistoryReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.MailHistoryReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.SmsHistoryReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.WechatHistoryReqDTO;
import com.zte.msg.pushcenter.pccore.entity.ApplicationInfo;
import com.zte.msg.pushcenter.pccore.entity.MailInfo;
import com.zte.msg.pushcenter.pccore.entity.SmsInfo;
import com.zte.msg.pushcenter.pccore.entity.WechatInfo;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/22 14:52
 */
public interface HistoryService {

    PageInfo<SmsInfo> listHistorySms(SmsHistoryReqDTO smsHistoryReqDTO);

    PageInfo<MailInfo> listHistoryMail(MailHistoryReqDTO mailHistoryReqDTO);

    void addHistorySms(SmsInfo smsInfo);

    void addHistoryMail(MailInfo mailInfo);

    PageInfo<WechatInfo> listHistoryWechat(WechatHistoryReqDTO wechatHistoryReqDTO);

    PageInfo<ApplicationInfo> listHistoryApplication(ApplicationHistoryReqDTO applicationHistoryReqDTO);
}
