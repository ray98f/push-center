package com.zte.msg.pushcenter.service;

import com.zte.msg.pushcenter.dto.OpenApiTokenInfo;
import com.zte.msg.pushcenter.entity.SecretKey;

import java.util.List;

/**
 * description:
 *
 * @author frp
 * @version 1.0
 * @date 2020/12/23 15:42
 */
public interface TokenService {
    int addSecretKey(SecretKey secretKey);

    SecretKey getSecretKey(String appId);

    int deleteSecretKey(String appId);

    List<SecretKey> listSecretKey();

    OpenApiTokenInfo selectTokenInfo(String appKey);

    String selectAppRole(String appKey);
}
