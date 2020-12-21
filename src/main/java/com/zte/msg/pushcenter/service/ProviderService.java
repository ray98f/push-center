package com.zte.msg.pushcenter.service;

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
     * 添加供应商
     *
     * @param providerReqDTO
     * @return
     */
    ProviderResDTO addProvider(ProviderReqDTO providerReqDTO);
}
