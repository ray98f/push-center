package com.zte.msg.pushcenter.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zte.msg.pushcenter.dto.PageReqDTO;
import com.zte.msg.pushcenter.dto.req.ConfigReqDTO;
import com.zte.msg.pushcenter.dto.res.ConfigResDTO;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/21 15:12
 */
public interface ConfigService {
    /**
     * 添加供应商配置
     *
     * @param configReqDTO
     * @return
     */
    ConfigResDTO addConfig(ConfigReqDTO configReqDTO);

    /**
     * 更新供应商配置
     *
     * @param configId
     * @param configReqDTO
     * @return
     */
    ConfigResDTO updateConfig(Long configId, ConfigReqDTO configReqDTO);

    /**
     * 删除
     *
     * @param configId
     */
    void deleteConfig(Long configId);

    /**
     * 根据id查询配置详情
     *
     * @param configId
     * @return
     */
    ConfigResDTO getConfigById(Long configId);

    /**
     * 分页查询第三方服务基本配置
     *
     * @param pageReqDTO
     * @return
     */
    Page<ConfigResDTO> getConfigs(PageReqDTO pageReqDTO);
}
