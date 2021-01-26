package com.zte.msg.pushcenter.pccore.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zte.msg.pushcenter.pccore.dto.req.ApplicationHistoryReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.MailHistoryReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.SmsHistoryReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.WechatHistoryReqDTO;
import com.zte.msg.pushcenter.pccore.entity.ApplicationInfo;
import com.zte.msg.pushcenter.pccore.entity.MailInfo;
import com.zte.msg.pushcenter.pccore.entity.SmsInfo;
import com.zte.msg.pushcenter.pccore.entity.WeChatInfo;
import com.zte.msg.pushcenter.pccore.mapper.*;
import com.zte.msg.pushcenter.pccore.service.HistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/22 14:54
 */
@Service
@Slf4j
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryMapper historyMapper;

    @Resource
    private SmsInfoMapper smsInfoMapper;

    @Resource
    private MailInfoMapper mailInfoMapper;

    @Resource
    private WeChatInfoMapper weChatInfoMapper;

    @Resource
    private ApplicationInfoMapper applicationInfoMapper;

    @Override
    public PageInfo<SmsInfo> listHistorySms(SmsHistoryReqDTO smsHistoryReqDTO) {
        PageHelper.startPage(smsHistoryReqDTO.getPage().intValue(), smsHistoryReqDTO.getSize().intValue());
        return historyMapper.listHistorySms(smsHistoryReqDTO);
    }

    @Override
    public PageInfo<MailInfo> listHistoryMail(MailHistoryReqDTO mailHistoryReqDTO) {
        PageHelper.startPage(mailHistoryReqDTO.getPage().intValue(), mailHistoryReqDTO.getSize().intValue());
        return historyMapper.listHistoryMail(mailHistoryReqDTO);
    }

    @Override
    public void addHistorySms(SmsInfo smsInfo) {
        smsInfoMapper.insert(smsInfo);
    }

    @Override
    public void addHistoryMail(MailInfo mailInfo) {
        mailInfoMapper.insert(mailInfo);
    }

    @Override
    public void addHistoryWeChat(WeChatInfo weChatInfo) {
        weChatInfoMapper.insert(weChatInfo);
    }

    @Override
    public void addApplicationInfo(ApplicationInfo applicationInfo) {
        applicationInfoMapper.insert(applicationInfo);
    }

    @Override
    public PageInfo<WeChatInfo> listHistoryWechat(WechatHistoryReqDTO wechatHistoryReqDTO) {
        PageHelper.startPage(wechatHistoryReqDTO.getPage().intValue(), wechatHistoryReqDTO.getSize().intValue());
        return historyMapper.listHistoryWechat(wechatHistoryReqDTO);
    }

    @Override
    public PageInfo<ApplicationInfo> listHistoryApplication(ApplicationHistoryReqDTO applicationHistoryReqDTO) {
        PageHelper.startPage(applicationHistoryReqDTO.getPage().intValue(), applicationHistoryReqDTO.getSize().intValue());
        return historyMapper.listHistoryApplication(applicationHistoryReqDTO);
    }

}
