package com.zte.msg.pushcenter.controller;

import com.alibaba.fastjson.JSONObject;
import com.zte.msg.pushcenter.dto.*;
import com.zte.msg.pushcenter.entity.SecretKey;
import com.zte.msg.pushcenter.enums.ErrorCode;
import com.zte.msg.pushcenter.exception.CommonException;
import com.zte.msg.pushcenter.service.TokenService;
import com.zte.msg.pushcenter.utils.Constants;
import com.zte.msg.pushcenter.utils.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * description:
 *
 * @author frp
 * @version 1.0
 * @date 2020/12/23 15:42
 */
@Slf4j
@RestController
@RequestMapping("/api/v1")
@Api(tags = "Token认证")
@Validated
public class TokenController {

    @Resource
    private TokenService tokenService;

    /**
     * 生成app密钥
     *
     * @param appId 服务ID
     * @return DataResponse
     */
    @PostMapping("/secretKey")
    @ApiOperation(value = "生成app密钥")
    public DataResponse<JSONObject> createSecretKey(@Valid String appId) {
        if (StringUtils.isBlank(appId)) {
            log.error("生成app密钥时appId返回为空");
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        SecretKey resultSecretKey = tokenService.getSecretKey(appId);
        if (!Objects.isNull(resultSecretKey)) {
            throw new CommonException(ErrorCode.DATA_EXIST);
        }
        SecretKey secretKey = new SecretKey();
        secretKey.setAppId(appId);
        secretKey.setAppKey(Constants.ZTE_NAME + TokenUtil.getTimestamp() + TokenUtil.getRandomString(5));
        secretKey.setAppSecret(TokenUtil.getUUID());
        int result = tokenService.addSecretKey(secretKey);
        if (result > 0) {
            log.info("app {} 密钥新增成功", appId);
            return DataResponse.success();
        } else {
            log.error("app {} 密钥新增失败", appId);
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }
    }

    /**
     * 根据appId获取app密钥
     *
     * @param appId 服务ID
     * @return DataResponse
     */
    @PutMapping("/secretKey")
    @ApiOperation(value = "根据appId获取app密钥")
    public DataResponse<SecretKey> getSecretKey(@Valid String appId) {
        if (StringUtils.isBlank(appId)) {
            log.error("生成app密钥时appId返回为空");
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        SecretKey secretKey = tokenService.getSecretKey(appId);
        if (!Objects.isNull(secretKey)) {
            log.info("app {} 密钥查询成功", appId);
            return DataResponse.of(secretKey);
        } else {
            log.error("app {} 密钥查询失败", appId);
            throw new CommonException(ErrorCode.SELECT_ERROR);
        }
    }

    @GetMapping("/secretKey")
    @ApiOperation(value = "根据appId获取app密钥")
    public DataResponse<List<SecretKey>> listSecretKey() {
        List<SecretKey> secretKey = tokenService.listSecretKey();
        if (!secretKey.isEmpty() && secretKey.size() > 0) {
            log.info("密钥列表查询成功");
            return DataResponse.of(secretKey);
        } else {
            log.error("密钥列表查询失败");
            throw new CommonException(ErrorCode.SELECT_ERROR);
        }
    }

}