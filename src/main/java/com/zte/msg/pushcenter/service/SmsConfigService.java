package com.zte.msg.pushcenter.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zte.msg.pushcenter.core.pusher.SmsPusher;
import com.zte.msg.pushcenter.dto.PageReqDTO;
import com.zte.msg.pushcenter.dto.req.SmsConfigReqDTO;
import com.zte.msg.pushcenter.dto.res.SmsConfigDetailResDTO;
import com.zte.msg.pushcenter.entity.SmsConfig;

import java.util.List;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/22 14:52
 */
public interface SmsConfigService {

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
    List<SmsPusher.ConfigDetail> selectConfigDetail(Integer type);
}
