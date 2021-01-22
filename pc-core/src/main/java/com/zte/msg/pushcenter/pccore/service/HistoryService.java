package com.zte.msg.pushcenter.pccore.service;

import com.github.pagehelper.PageInfo;
import com.zte.msg.pushcenter.pccore.dto.req.ApplicationHistoryReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.MailHistoryReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.SmsHistoryReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.WechatHistoryReqDTO;
import com.zte.msg.pushcenter.pccore.entity.ApplicationInfo;
import com.zte.msg.pushcenter.pccore.entity.MailInfo;
import com.zte.msg.pushcenter.pccore.entity.SmsInfo;
import com.zte.msg.pushcenter.pccore.entity.WeChatInfo;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/22 14:52
 */
public interface HistoryService {

    /**
     * 查询短信历史记录
     *
     * @param smsHistoryReqDTO
     * @return
     */
    PageInfo<SmsInfo> listHistorySms(SmsHistoryReqDTO smsHistoryReqDTO);

    /**
     * 查询邮件历史记录
     *
     * @param mailHistoryReqDTO
     * @return
     */
    PageInfo<MailInfo> listHistoryMail(MailHistoryReqDTO mailHistoryReqDTO);

    /**
     * 新增短信推送记录
     *
     * @param smsInfo
     */
    void addHistorySms(SmsInfo smsInfo);

    /**
     * 新增邮件推送记录
     *
     * @param mailInfo
     */
    void addHistoryMail(MailInfo mailInfo);

    /**
     * 新增微信推送历史记录
     *
     * @param weChatInfo
     */
    void addHistoryWeChat(WeChatInfo weChatInfo);

    /**
     * 新增app推送历史记录
     *
     * @param applicationInfo
     */
    void addApplicationInfo(ApplicationInfo applicationInfo);

    /**
     * 分页查询微信推送历史记录
     *
     * @param wechatHistoryReqDTO
     * @return
     */
    PageInfo<WeChatInfo> listHistoryWechat(WechatHistoryReqDTO wechatHistoryReqDTO);

    /**
     * 分页查询app推送历史记录
     *
     * @param applicationHistoryReqDTO
     * @return
     */
    PageInfo<ApplicationInfo> listHistoryApplication(ApplicationHistoryReqDTO applicationHistoryReqDTO);
}
