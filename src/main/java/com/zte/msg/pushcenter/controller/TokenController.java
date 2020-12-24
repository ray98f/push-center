package com.zte.msg.pushcenter.controller;

import com.alibaba.fastjson.JSONObject;
import com.zte.msg.pushcenter.dto.*;
import com.zte.msg.pushcenter.entity.SecretKey;
import com.zte.msg.pushcenter.dto.TokenInfo;
import com.zte.msg.pushcenter.enums.ErrorCode;
import com.zte.msg.pushcenter.exception.CommonException;
import com.zte.msg.pushcenter.service.TokenService;
import com.zte.msg.pushcenter.utils.AesUtils;
import com.zte.msg.pushcenter.utils.Constants;
import com.zte.msg.pushcenter.utils.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

import static com.zte.msg.pushcenter.utils.TokenUtil.*;

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
    public DataResponse<JSONObject> createSecretKey(@Valid @RequestParam @NotBlank(message = "32000006") String appId) {
        SecretKey resultSecretKey = tokenService.getSecretKey(appId);
        if (!Objects.isNull(resultSecretKey)) {
            throw new CommonException(ErrorCode.DATA_EXIST);
        }
        SecretKey secretKey = new SecretKey();
        secretKey.setAppId(appId);
        secretKey.setAppKey(Constants.ZTE_NAME + TokenUtil.getTimestamp() + TokenUtil.getRandomString(5));
        secretKey.setAppSecret(TokenUtil.getUUID() + TokenUtil.getRandomString(16));
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
    public DataResponse<SecretKey> getSecretKey(@Valid @RequestParam @NotBlank(message = "32000006") String appId) {
        SecretKey secretKey = tokenService.getSecretKey(appId);
        if (!Objects.isNull(secretKey)) {
            log.info("app {} 密钥查询成功", appId);
            return DataResponse.of(secretKey);
        } else {
            log.error("app {} 密钥查询失败", appId);
            throw new CommonException(ErrorCode.SELECT_ERROR);
        }
    }

    /**
     * 密钥列表获取
     *
     * @return DataResponse
     */
    @GetMapping("/secretKey")
    @ApiOperation(value = "密钥列表获取")
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

    @PostMapping("/openapi/token")
    @ApiOperation(value = "第三方服务Token获取")
    public DataResponse<String> openApiToken(@RequestBody @Valid @ApiParam(value = "第三方服务信息") TokenInfo tokenInfo) throws Exception {
        if (Objects.isNull(tokenInfo) || StringUtils.isAnyBlank(tokenInfo.getAppKey(), tokenInfo.getAppSecret())) {
            log.error("第三方服务信息为空");
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        TokenInfo info = tokenService.selectTokenInfo(tokenInfo.getAppKey(), tokenInfo.getAppSecret());
        if (Objects.isNull(info)) {
            log.error("数据库第三方服务信息获取失败");
            throw new CommonException(ErrorCode.DATA_EXIST);
        }
        tokenInfo.setAppName(info.getAppName());
        tokenInfo.setRole(info.getRole());
        String token = CreateToken(tokenInfo);
        String jsonToken = "{'appKey':'" + tokenInfo.getAppKey() + "', 'appSecret':'" + tokenInfo.getAppSecret() + "', 'token':'" + token + "}'";
        jsonToken = AesUtils.encrypt(jsonToken);
        log.info("{} Token返回成功", tokenInfo.getAppId());
        return DataResponse.of(jsonToken);
    }


    @PostMapping("/token")
    @ApiOperation(value = "平台登录Token获取")
    //TODO 接口补充
    public DataResponse<JSONObject> centerToken() {
        return DataResponse.success();
    }

}