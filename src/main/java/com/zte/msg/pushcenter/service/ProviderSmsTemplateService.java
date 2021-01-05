package com.zte.msg.pushcenter.service;

import com.zte.msg.pushcenter.entity.ProviderSmsTemplate;

import java.util.List;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/31 15:26
 */
public interface ProviderSmsTemplateService {

    void saveBatch(List<ProviderSmsTemplate> templates);
}
