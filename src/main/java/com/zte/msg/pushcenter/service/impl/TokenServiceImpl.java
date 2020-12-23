package com.zte.msg.pushcenter.service.impl;

import com.zte.msg.pushcenter.dao.TokenDao;
import com.zte.msg.pushcenter.entity.SecretKey;
import com.zte.msg.pushcenter.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
