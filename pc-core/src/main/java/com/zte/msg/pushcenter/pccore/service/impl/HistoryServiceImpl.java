package com.zte.msg.pushcenter.pccore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zte.msg.pushcenter.pccore.dto.req.MailHistoryReqDTO;
import com.zte.msg.pushcenter.pccore.entity.MailInfo;
import com.zte.msg.pushcenter.pccore.entity.Sms;
import com.zte.msg.pushcenter.pccore.entity.SmsConfig;
import com.zte.msg.pushcenter.pccore.enums.ErrorCode;
import com.zte.msg.pushcenter.pccore.exception.CommonException;
import com.zte.msg.pushcenter.pccore.mapper.AppMapper;
import com.zte.msg.pushcenter.pccore.mapper.HistoryMapper;
import com.zte.msg.pushcenter.pccore.service.HistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public List<Sms> listHistorySms() {
        return historyMapper.listHistorySms();
    }

    @Override
    public PageInfo<MailInfo> listHistoryMail(MailHistoryReqDTO mailHistoryReqDTO) {
        if (null == mailHistoryReqDTO.getPage() || null == mailHistoryReqDTO.getSize()) {
            throw new CommonException(ErrorCode.PAGE_PARAM_EMPTY);
        }
        if (0 >= mailHistoryReqDTO.getPage() || 0 >= mailHistoryReqDTO.getSize()){
            throw new CommonException(ErrorCode.PAGE_PARAM_ERROR);
        }
        PageHelper.startPage(mailHistoryReqDTO.getPage().intValue(), mailHistoryReqDTO.getSize().intValue());
        List<MailInfo> list = historyMapper.listHistoryMail(mailHistoryReqDTO);
        return new PageInfo<>(list);
    }
}
