package com.zte.msg.pushcenter.pccore.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zte.msg.pushcenter.pccore.dto.req.MailHistoryReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.SmsHistoryReqDTO;
import com.zte.msg.pushcenter.pccore.entity.MailInfo;
import com.zte.msg.pushcenter.pccore.entity.SmsInfo;
import com.zte.msg.pushcenter.pccore.enums.ErrorCode;
import com.zte.msg.pushcenter.pccore.exception.CommonException;
import com.zte.msg.pushcenter.pccore.mapper.HistoryMapper;
import com.zte.msg.pushcenter.pccore.mapper.MailInfoMapper;
import com.zte.msg.pushcenter.pccore.mapper.SmsInfoMapper;
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
}
