package com.zte.msg.pushcenter.pccore.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zte.msg.pushcenter.pccore.dto.PageReqDTO;
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
import org.springframework.beans.BeanUtils;
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
    public Page<SmsInfo> listHistorySms(SmsHistoryReqDTO smsHistoryReqDTO) {
        PageReqDTO pageReqDTO = new PageReqDTO();
        BeanUtils.copyProperties(smsHistoryReqDTO, pageReqDTO);
        PageHelper.startPage(smsHistoryReqDTO.getPage().intValue(), smsHistoryReqDTO.getSize().intValue());
        return historyMapper.listHistorySms(pageReqDTO.of(), smsHistoryReqDTO);
    }

    @Override
    public Page<MailInfo> listHistoryMail(MailHistoryReqDTO mailHistoryReqDTO) {
        PageReqDTO pageReqDTO = new PageReqDTO();
        BeanUtils.copyProperties(mailHistoryReqDTO, pageReqDTO);
        PageHelper.startPage(mailHistoryReqDTO.getPage().intValue(), mailHistoryReqDTO.getSize().intValue());
        return historyMapper.listHistoryMail(pageReqDTO.of(), mailHistoryReqDTO);
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
    public Page<WeChatInfo> listHistoryWechat(WechatHistoryReqDTO wechatHistoryReqDTO) {
        PageReqDTO pageReqDTO = new PageReqDTO();
        BeanUtils.copyProperties(wechatHistoryReqDTO, pageReqDTO);
        PageHelper.startPage(wechatHistoryReqDTO.getPage().intValue(), wechatHistoryReqDTO.getSize().intValue());
        return historyMapper.listHistoryWechat(pageReqDTO.of(), wechatHistoryReqDTO);
    }

    @Override
    public Page<ApplicationInfo> listHistoryApplication(ApplicationHistoryReqDTO applicationHistoryReqDTO) {
        PageReqDTO pageReqDTO = new PageReqDTO();
        BeanUtils.copyProperties(applicationHistoryReqDTO, pageReqDTO);
        PageHelper.startPage(applicationHistoryReqDTO.getPage().intValue(), applicationHistoryReqDTO.getSize().intValue());
        return historyMapper.listHistoryApplication(pageReqDTO.of(), applicationHistoryReqDTO);
    }

}
