package com.zte.msg.pushcenter.dao;

import com.zte.msg.pushcenter.entity.SecretKey;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenDao {

    int addSecretKey(SecretKey secretKey);
}
