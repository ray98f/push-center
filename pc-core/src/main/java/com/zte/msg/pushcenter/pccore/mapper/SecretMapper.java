package com.zte.msg.pushcenter.pccore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zte.msg.pushcenter.pccore.dto.OpenApiTokenInfo;
import com.zte.msg.pushcenter.pccore.dto.res.SecretKeyResDTO;
import com.zte.msg.pushcenter.pccore.entity.SecretKey;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author frp
 */
@Repository
public interface SecretMapper extends BaseMapper<SecretKey> {

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
