package com.zte.msg.pushcenter.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zte.msg.pushcenter.dto.PageReqDTO;
import com.zte.msg.pushcenter.dto.req.ProviderReqDTO;
import com.zte.msg.pushcenter.dto.res.ProviderResDTO;

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
    ProviderResDTO addProvider(ProviderReqDTO providerReqDTO);

    /**
     * 更新供应商配置
     *
     * @param providerId
     * @param providerReqDTO
     * @return
     */
    ProviderResDTO updateProvider(Long providerId, ProviderReqDTO providerReqDTO);

    /**
     * 删除
     *
     * @param providerId
     */
    void deleteProvider(Long providerId);

    /**
     * 根据id查询配置详情
     *
     * @param providerId
     * @return
     */
    ProviderResDTO getProviderById(Long providerId);

    /**
     * 分页查询第三方服务基本配置
     *
     * @param pageReqDTO
     * @return
     */
    Page<ProviderResDTO> getProviders(PageReqDTO pageReqDTO);
}
