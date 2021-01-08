package com.zte.msg.pushcenter.pccore.service.impl;

import com.zte.msg.pushcenter.pccore.dto.res.SecretKeyResDTO;
import com.zte.msg.pushcenter.pccore.enums.ErrorCode;
import com.zte.msg.pushcenter.pccore.exception.CommonException;
import com.zte.msg.pushcenter.pccore.mapper.SecretMapper;
import com.zte.msg.pushcenter.pccore.service.SecretService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * description:
 *
 * @author frp
 * @version 1.0
 * @date 2020/12/23 15:42
 */
@Service
@Slf4j
public class SecretServiceImpl implements SecretService {

    @Autowired
    private SecretMapper secretMapper;

    @Override
    public String selectAppRole(String appKey) {
        return secretMapper.selectAppRole(appKey);
    }

    @Override
    public String selectAppSecret(Integer appId){
        return secretMapper.selectAppSecret(appId);
    }

    @Override
    public SecretKeyResDTO selectSecretKey(Integer appId){
        SecretKeyResDTO secretKeyResDTO = secretMapper.selectSecretKey(appId);
        if (Objects.isNull(secretKeyResDTO)){
            throw new CommonException(ErrorCode.SELECT_EMPTY);
        }
        return secretKeyResDTO;
    }

}
