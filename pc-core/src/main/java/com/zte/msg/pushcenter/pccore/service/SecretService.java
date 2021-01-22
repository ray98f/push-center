package com.zte.msg.pushcenter.pccore.service;


import com.zte.msg.pushcenter.pccore.dto.OpenApiTokenInfo;
import com.zte.msg.pushcenter.pccore.dto.res.SecretKeyResDTO;
import com.zte.msg.pushcenter.pccore.entity.SecretKey;

import java.util.List;

/**
 * description:
 *
 * @author frp
 * @version 1.0
 * @date 2020/12/23 15:42
 */
public interface SecretService {

    /**
     * 获取应用权限
     * @param appKey
     * @return
     */
    String selectAppRole(String appKey);

    /**
     * 获取应用密钥
     * @param appId
     * @return
     */
    String selectAppSecret(Long appId);

    /**
     * 查询应用密钥
     * @param appId
     * @return
     */
    SecretKeyResDTO selectSecretKey(Integer appId);
}
