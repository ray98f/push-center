package com.zte.msg.pushcenter.service.impl;

import com.zte.msg.pushcenter.dao.TokenDao;
import com.zte.msg.pushcenter.dto.OpenApiTokenInfo;
import com.zte.msg.pushcenter.entity.SecretKey;
import com.zte.msg.pushcenter.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description:
 *
 * @author frp
 * @version 1.0
 * @date 2020/12/23 15:42
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenDao tokenDao;

    @Override
    public int addSecretKey(SecretKey secretKey){
        return tokenDao.addSecretKey(secretKey);
    }

    @Override
    public SecretKey getSecretKey(String appId){
        return tokenDao.getSecretKey(appId);
    }

    @Override
    public int deleteSecretKey(String appId){
        return tokenDao.deleteSecretKey(appId);
    }

    @Override
    public List<SecretKey> listSecretKey(){
        return tokenDao.listSecretKey();
    }

    @Override
    public OpenApiTokenInfo selectTokenInfo(String appKey){
        return tokenDao.selectTokenInfo(appKey);
    }

    @Override
    public String selectAppRole(String appKey){
        return tokenDao.selectAppRole(appKey);
    }
}
