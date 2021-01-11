package com.zte.msg.pushcenter.pccore.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zte.msg.pushcenter.pccore.dto.PageReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.ProviderReqDTO;
import com.zte.msg.pushcenter.pccore.dto.res.ProviderResDTO;
import com.zte.msg.pushcenter.pccore.model.ScriptModel;
import com.zte.msg.pushcenter.pccore.model.SmsConfigDetailModel;

import java.util.List;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/21 15:12
 */
public interface ProviderService {
    /**
     * 添加供应商配置
     *
     * @param providerReqDTO
     * @return
     */
    void addProvider(ProviderReqDTO providerReqDTO);

    /**
     * 更新供应商配置
     *
     * @param providerId
     * @param providerReqDTO
     * @return
     */
    void updateProvider(Long providerId, ProviderReqDTO providerReqDTO);

    /**
     * 删除
     *
     * @param providerIds
     */
    void deleteProvider(Long[] providerIds);

    /**
     * 根据id查询配置详情
     *
     * @param providerId
     * @return
     */
    ProviderResDTO getProviderById(Long providerId);

    /**
     * 分页查询消息平台配置
     *
     * @param pageReqDTO
     * @return
     */
    Page<ProviderResDTO> getProviders(String provider,
                                      Integer type,
                                      Integer status,
                                      PageReqDTO pageReqDTO);

    List<ScriptModel> getScripts();

    List<SmsConfigDetailModel> getAllSmsConfigForInit();

}
