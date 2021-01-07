package com.zte.msg.pushcenter.pccore.service.impl;

import com.zte.msg.pushcenter.pccore.mapper.SecretMapper;
import com.zte.msg.pushcenter.pccore.service.SecretService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class SecretServiceImpl implements SecretService {

    @Autowired
    private SecretMapper secretMapper;

    @Override
    public String selectAppRole(String appKey) {
        return secretMapper.selectAppRole(appKey);
    }
}
