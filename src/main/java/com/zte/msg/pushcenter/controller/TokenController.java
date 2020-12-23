package com.zte.msg.pushcenter.controller;

import com.alibaba.fastjson.JSONObject;
import com.zte.msg.pushcenter.dto.*;
import com.zte.msg.pushcenter.entity.SecretKey;
import com.zte.msg.pushcenter.service.TokenService;
import com.zte.msg.pushcenter.utils.Constants;
import com.zte.msg.pushcenter.utils.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * description:
 *
 * @author frp
 * @version 1.0
 * @date 2020/12/23 15:42
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/token")
@Api(tags = "Token认证")
@Validated
public class TokenController {

    @Resource
    private TokenService tokenService;

    @PostMapping("/secretKey")
    @ApiOperation(value = "生成app密钥")
    public DataResponse<JSONObject> createSecretKey(@Valid String appId) {
        if (StringUtils.isBlank(appId)) {
            log.error("生成app密钥时appId返回为空");
            return DataResponse.error();
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
            return DataResponse.error();
        }
    }
}
