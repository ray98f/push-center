package com.zte.msg.pushcenter.pccore.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.zte.msg.pushcenter.pccore.dto.PageReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.ApplicationHistoryReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.MailHistoryReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.SmsHistoryReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.WechatHistoryReqDTO;
import com.zte.msg.pushcenter.pccore.entity.ApplicationInfo;
import com.zte.msg.pushcenter.pccore.entity.MailInfo;
import com.zte.msg.pushcenter.pccore.entity.SmsInfo;
import com.zte.msg.pushcenter.pccore.entity.WeChatInfo;
import com.zte.msg.pushcenter.pccore.mapper.HistoryMapper;
import com.zte.msg.pushcenter.pccore.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

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

    private static final BlockingQueue<WeChatInfo> WE_CHAT_INFO_BLOCKING_QUEUE = new LinkedBlockingQueue<>(1000);

    private static final BlockingQueue<SmsInfo> SMS_INFO_BLOCKING_QUEUE = new LinkedBlockingQueue<>(1000);

    private static final BlockingQueue<MailInfo> MAIL_INFO_BLOCKING_QUEUE = new LinkedBlockingQueue<>(1000);

    private static final BlockingQueue<ApplicationInfo> APP_INFO_BLOCKING_QUEUE = new LinkedBlockingQueue<>(1000);

    private static final int BATCH_INSERT_COUNT = 500;

    @Resource
    private WechatInfoService wechatInfoService;

    @Resource
    private SmsInfoService smsInfoService;

    @Resource
    private MailInfoService mailInfoService;

    @Resource
    private ApplicationService applicationService;

    @Autowired
    private HistoryMapper historyMapper;


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


    @Override
    public void addHistorySms(SmsInfo smsInfo) {
        SMS_INFO_BLOCKING_QUEUE.add(smsInfo);
        if (SMS_INFO_BLOCKING_QUEUE.size() >= BATCH_INSERT_COUNT) {
            insertSmsInfo();
        }
    }

    @Override
    public void addHistoryMail(MailInfo mailInfo) {
        MAIL_INFO_BLOCKING_QUEUE.add(mailInfo);
        if (MAIL_INFO_BLOCKING_QUEUE.size() >= BATCH_INSERT_COUNT) {
            insertMailInfo();
        }
    }

    @Override
    public void addApplicationInfo(ApplicationInfo applicationInfo) {
        APP_INFO_BLOCKING_QUEUE.add(applicationInfo);
        if (APP_INFO_BLOCKING_QUEUE.size() >= BATCH_INSERT_COUNT) {
            insertAppInfo();
        }
    }


    @Override
    public void addHistoryWeChat(WeChatInfo weChatInfo) {
        WE_CHAT_INFO_BLOCKING_QUEUE.add(weChatInfo);
        if (WE_CHAT_INFO_BLOCKING_QUEUE.size() >= 500) {
            insertWechatInfo();
        }
    }

    private void insertAppInfo() {
        if (APP_INFO_BLOCKING_QUEUE.isEmpty()) {
            return;
        }
        List<ApplicationInfo> applicationInfos = new ArrayList<>();
        APP_INFO_BLOCKING_QUEUE.drainTo(applicationInfos);
        applicationService.saveBatch(applicationInfos);
        log.info("Batch persist app infos success, size : {}", applicationInfos.size());
    }

    private void insertSmsInfo() {
        if (SMS_INFO_BLOCKING_QUEUE.isEmpty()) {
            return;
        }
        List<SmsInfo> smsInfos = new ArrayList<>();
        SMS_INFO_BLOCKING_QUEUE.drainTo(smsInfos);
        smsInfoService.saveBatch(smsInfos);
        log.info("Batch persist sms infos success, size : {}", smsInfos.size());
    }

    private void insertMailInfo() {
        if (MAIL_INFO_BLOCKING_QUEUE.isEmpty()) {
            return;
        }
        List<MailInfo> mailInfos = new ArrayList<>();
        MAIL_INFO_BLOCKING_QUEUE.drainTo(mailInfos);
        mailInfoService.saveBatch(mailInfos);
        log.info("Batch persist mail infos success, size : {}", mailInfos.size());
    }

    private void insertWechatInfo() {
        if (WE_CHAT_INFO_BLOCKING_QUEUE.isEmpty()) {
            return;
        }
        List<WeChatInfo> weChatInfos = new ArrayList<>();
        WE_CHAT_INFO_BLOCKING_QUEUE.drainTo(weChatInfos);
        wechatInfoService.saveBatch(weChatInfos);
        log.info("Batch persist wechat infos success, size : {}", weChatInfos.size());
    }

    @Scheduled(cron = "0/5 * * * * ?")
    public void scheduledInsertTask() {
        insertSmsInfo();
        insertWechatInfo();
        insertMailInfo();
        insertAppInfo();
    }

}
