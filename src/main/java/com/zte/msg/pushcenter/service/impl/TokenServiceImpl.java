package com.zte.msg.pushcenter.service.impl;

import com.zte.msg.pushcenter.dto.DataResponse;
import com.zte.msg.pushcenter.enums.ErrorCode;
import com.zte.msg.pushcenter.exception.CommonException;
import com.zte.msg.pushcenter.mapper.TokenMapper;
import com.zte.msg.pushcenter.dto.OpenApiTokenInfo;
import com.zte.msg.pushcenter.entity.SecretKey;
import com.zte.msg.pushcenter.service.TokenService;
import com.zte.msg.pushcenter.utils.Constants;
import com.zte.msg.pushcenter.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenMapper tokenMapper;

    @Override
    public int addSecretKey(Integer appId){
        SecretKey resultSecretKey = tokenMapper.getSecretKey(appId);
        if (!Objects.isNull(resultSecretKey)) {
            throw new CommonException(ErrorCode.DATA_EXIST);
        }
        SecretKey secretKey = new SecretKey();
        secretKey.setAppId(appId);
        secretKey.setAppKey(Constants.ZTE_NAME + TokenUtil.getTimestamp() + TokenUtil.getRandomString(5));
        secretKey.setAppSecret(TokenUtil.getUUID() + TokenUtil.getRandomString(16));
        return tokenMapper.addSecretKey(secretKey);
    }

    @Override
    public SecretKey getSecretKey(Integer appId){
        SecretKey secretKey = tokenMapper.getSecretKey(appId);
        if (!Objects.isNull(secretKey)) {
            log.info("app {} 密钥查询成功", appId);
            return secretKey;
        } else {
            log.error("app {} 密钥查询失败", appId);
            throw new CommonException(ErrorCode.SELECT_ERROR);
        }
    }

    @Override
    public int deleteSecretKey(Integer appId){
        return tokenMapper.deleteSecretKey(appId);
    }

    @Override
    public List<SecretKey> listSecretKey(){
        List<SecretKey> secretKey = tokenMapper.listSecretKey();
        if (secretKey.size() != 0) {
            log.info("密钥列表查询成功");
            return secretKey;
        } else {
            log.error("密钥列表查询失败");
            throw new CommonException(ErrorCode.SELECT_ERROR);
        }
    }

    @Override
    public OpenApiTokenInfo selectTokenInfo(String appKey){

        return tokenMapper.selectTokenInfo(appKey);
    }

    @Override
    public String selectAppRole(String appKey){
        return tokenMapper.selectAppRole(appKey);
    }
}
