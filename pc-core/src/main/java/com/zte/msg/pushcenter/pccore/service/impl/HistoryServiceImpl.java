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
import com.zte.msg.pushcenter.pccore.enums.ErrorCode;
import com.zte.msg.pushcenter.pccore.exception.CommonException;
import com.zte.msg.pushcenter.pccore.mapper.HistoryMapper;
import com.zte.msg.pushcenter.pccore.mapper.MailInfoMapper;
import com.zte.msg.pushcenter.pccore.mapper.SmsInfoMapper;
import com.zte.msg.pushcenter.pccore.mapper.WeChatInfoMapper;
import com.zte.msg.pushcenter.pccore.service.HistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    private WeChatInfoMapper weChatInfoMapper;

    @Override
    public PageInfo<SmsInfo> listHistorySms(SmsHistoryReqDTO smsHistoryReqDTO) {
        if (null == smsHistoryReqDTO.getPage() || null == smsHistoryReqDTO.getSize()) {
            throw new CommonException(ErrorCode.PAGE_PARAM_EMPTY);
        }
        if (0 >= smsHistoryReqDTO.getPage() || 0 >= smsHistoryReqDTO.getSize()) {
            throw new CommonException(ErrorCode.PAGE_PARAM_ERROR);
        }
        PageHelper.startPage(smsHistoryReqDTO.getPage().intValue(), smsHistoryReqDTO.getSize().intValue());
        List<SmsInfo> list = historyMapper.listHistorySms(smsHistoryReqDTO);
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<MailInfo> listHistoryMail(MailHistoryReqDTO mailHistoryReqDTO) {
        if (null == mailHistoryReqDTO.getPage() || null == mailHistoryReqDTO.getSize()) {
            throw new CommonException(ErrorCode.PAGE_PARAM_EMPTY);
        }
        if (0 >= mailHistoryReqDTO.getPage() || 0 >= mailHistoryReqDTO.getSize()) {
            throw new CommonException(ErrorCode.PAGE_PARAM_ERROR);
        }
        PageHelper.startPage(mailHistoryReqDTO.getPage().intValue(), mailHistoryReqDTO.getSize().intValue());
        List<MailInfo> list = historyMapper.listHistoryMail(mailHistoryReqDTO);
        return new PageInfo<>(list);
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
    public PageInfo<WeChatInfo> listHistoryWechat(WechatHistoryReqDTO wechatHistoryReqDTO) {
        if (null == wechatHistoryReqDTO.getPage() || null == wechatHistoryReqDTO.getSize()) {
            throw new CommonException(ErrorCode.PAGE_PARAM_EMPTY);
        }
        if (0 >= wechatHistoryReqDTO.getPage() || 0 >= wechatHistoryReqDTO.getSize()) {
            throw new CommonException(ErrorCode.PAGE_PARAM_ERROR);
        }
        PageHelper.startPage(wechatHistoryReqDTO.getPage().intValue(), wechatHistoryReqDTO.getSize().intValue());

        //TODO 接口完成
        return new PageInfo<>(historyMapper.listHistoryWechat(wechatHistoryReqDTO));
    }

    @Override
    public PageInfo<ApplicationInfo> listHistoryApplication(ApplicationHistoryReqDTO applicationHistoryReqDTO) {
        if (null == applicationHistoryReqDTO.getPage() || null == applicationHistoryReqDTO.getSize()) {
            throw new CommonException(ErrorCode.PAGE_PARAM_EMPTY);
        }
        if (0 >= applicationHistoryReqDTO.getPage() || 0 >= applicationHistoryReqDTO.getSize()) {
            throw new CommonException(ErrorCode.PAGE_PARAM_ERROR);
        }
        PageHelper.startPage(applicationHistoryReqDTO.getPage().intValue(), applicationHistoryReqDTO.getSize().intValue());
        List<ApplicationInfo> list = historyMapper.listHistoryApplication(applicationHistoryReqDTO);
        return new PageInfo<>(list);
    }

}
