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
    String selectAppRole(String appKey);

    String selectAppSecret(Long appId);

    SecretKeyResDTO selectSecretKey(Integer appId);
}
