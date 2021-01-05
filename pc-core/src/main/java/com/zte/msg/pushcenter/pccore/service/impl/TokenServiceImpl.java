package com.zte.msg.pushcenter.pccore.service.impl;

import com.zte.msg.pushcenter.pccore.dto.OpenApiTokenInfo;
import com.zte.msg.pushcenter.pccore.entity.SecretKey;
import com.zte.msg.pushcenter.pccore.enums.ErrorCode;
import com.zte.msg.pushcenter.pccore.exception.CommonException;
import com.zte.msg.pushcenter.pccore.mapper.TokenMapper;
import com.zte.msg.pushcenter.pccore.service.TokenService;
import com.zte.msg.pushcenter.pccore.utils.Constants;
import com.zte.msg.pushcenter.pccore.utils.TokenUtil;
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
    public void addSecretKey(Integer appId) {
        SecretKey resultSecretKey = tokenMapper.getSecretKey(appId);
        if (!Objects.isNull(resultSecretKey)) {
            throw new CommonException(ErrorCode.DATA_EXIST);
        }
        SecretKey secretKey = new SecretKey();
        secretKey.setAppId(appId);
        secretKey.setAppKey(Constants.ZTE_NAME + TokenUtil.getTimestamp() + TokenUtil.getRandomString(5));
        secretKey.setAppSecret(TokenUtil.getUUID() + TokenUtil.getRandomString(16));
        int result = tokenMapper.addSecretKey(secretKey);
        if (result > 0) {
            log.info("app {} 密钥新增成功", appId);
        } else {
            log.error("app {} 密钥新增失败", appId);
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }
    }

    @Override
    public SecretKey getSecretKey(Integer appId) {
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
    public void deleteSecretKey(Integer appId) {
        int result = tokenMapper.deleteSecretKey(appId);
        if (result > 0) {
            log.info("密钥删除成功");
        } else {
            log.error("密钥删除失败");
            throw new CommonException(ErrorCode.DELETE_ERROR);
        }
    }

    @Override
    public List<SecretKey> listSecretKey() {
        List<SecretKey> secretKey = tokenMapper.listSecretKey();
        if (null != secretKey && !secretKey.isEmpty()) {
            log.info("密钥列表查询成功");
            return secretKey;
        } else {
            log.error("密钥列表查询失败");
            throw new CommonException(ErrorCode.SELECT_EMPTY);
        }
    }

    @Override
    public OpenApiTokenInfo selectTokenInfo(String appKey) {
        OpenApiTokenInfo info = tokenMapper.selectTokenInfo(appKey);
        if (Objects.isNull(info)) {
            log.error("数据库第三方服务信息获取失败");
            throw new CommonException(ErrorCode.SELECT_ERROR);
        }
        return info;
    }

    @Override
    public String selectAppRole(String appKey) {
        return tokenMapper.selectAppRole(appKey);
    }
}
