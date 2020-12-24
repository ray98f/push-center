package com.zte.msg.pushcenter.dao;

import com.zte.msg.pushcenter.dto.TokenInfo;
import com.zte.msg.pushcenter.entity.SecretKey;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokenDao {

    int addSecretKey(SecretKey secretKey);

    SecretKey getSecretKey(String appId);

    List<SecretKey> listSecretKey();

    TokenInfo selectTokenInfo(String appKey, String appSecret);
}
