package com.zte.msg.pushcenter.pccore.service;


import com.zte.msg.pushcenter.pccore.dto.res.ProviderSmsTemplateResDTO;
import com.zte.msg.pushcenter.pccore.entity.ProviderSmsTemplate;

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

    List<ProviderSmsTemplateResDTO> getProviderSmsTemplateList(List<Long> templateIds);

}
