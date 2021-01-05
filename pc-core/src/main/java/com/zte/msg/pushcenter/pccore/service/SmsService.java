package com.zte.msg.pushcenter.pccore.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zte.msg.pushcenter.pccore.core.pusher.SmsPusher;
import com.zte.msg.pushcenter.pccore.dto.PageReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.SmsConfigReqDTO;
import com.zte.msg.pushcenter.pccore.dto.res.SmsConfigDetailResDTO;
import com.zte.msg.pushcenter.pccore.entity.Sms;
import com.zte.msg.pushcenter.pccore.entity.SmsConfig;

import java.util.List;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/22 14:52
 */
public interface SmsService {

    SmsConfig getById(Long id);

    SmsConfigDetailResDTO getSmsConfig(Long id);

    SmsConfigDetailResDTO addSmsConfig(SmsConfigReqDTO reqDTO);

    void updateSmsConfig(Long smsConfigId, SmsConfigReqDTO smsConfig);

    void deleteSmsConfig(Long deleteSmsConfig);

    Page<SmsConfigDetailResDTO> getSmsConfigs(PageReqDTO page);

    /**
     * 查询全量配置
     *
     * @return
     */
    List<SmsPusher.ConfigDetail> selectAllSmsConfigForInit();

    List<Sms> listHistorySms();
}
