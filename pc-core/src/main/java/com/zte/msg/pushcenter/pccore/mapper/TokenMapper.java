package com.zte.msg.pushcenter.pccore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zte.msg.pushcenter.pccore.dto.OpenApiTokenInfo;
import com.zte.msg.pushcenter.pccore.entity.SecretKey;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokenMapper extends BaseMapper<SecretKey> {

    int addSecretKey(SecretKey secretKey);

    SecretKey getSecretKey(Integer appId);

    int deleteSecretKey(Integer appId);

    List<SecretKey> listSecretKey();

    OpenApiTokenInfo selectTokenInfo(String appKey);

    String selectAppRole(String appKey);
}
