package com.zte.msg.pushcenter.dao;

import com.zte.msg.pushcenter.dto.OpenApiTokenInfo;
import com.zte.msg.pushcenter.entity.SecretKey;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokenDao {

    int addSecretKey(SecretKey secretKey);

    SecretKey getSecretKey(String appId);

    int deleteSecretKey(String appId);

    List<SecretKey> listSecretKey();

    OpenApiTokenInfo selectTokenInfo(String appKey);

    String selectAppRole(String appKey);
}
