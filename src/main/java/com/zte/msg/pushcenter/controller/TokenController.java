package com.zte.msg.pushcenter.controller;

import com.zte.msg.pushcenter.dto.*;
import com.zte.msg.pushcenter.entity.SecretKey;
import com.zte.msg.pushcenter.dto.OpenApiTokenInfo;
import com.zte.msg.pushcenter.enums.ErrorCode;
import com.zte.msg.pushcenter.exception.CommonException;
import com.zte.msg.pushcenter.service.TokenService;
import com.zte.msg.pushcenter.utils.AesUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Resource
    private HttpServletRequest request;


    /**
     * 生成app密钥
     *
     * @param appId 服务ID
     * @return DataResponse
     */
    @PostMapping("/secretKey")
    @ApiOperation(value = "生成app密钥")
    public <T> DataResponse<T> createSecretKey(@Valid @RequestParam @NotNull(message = "32000006") Integer appId) {
        int result = tokenService.addSecretKey(appId);
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
    public DataResponse<SecretKey> getSecretKey(@Valid @RequestParam @NotNull(message = "32000006") Integer appId) {
        return DataResponse.of(tokenService.getSecretKey(appId));
    }

    /**
     * 密钥列表获取
     *
     * @return DataResponse
     */
    @GetMapping("/secretKey")
    @ApiOperation(value = "密钥列表获取")
    public DataResponse<List<SecretKey>> listSecretKey() {
        return DataResponse.of(tokenService.listSecretKey());
    }

    /**
     * 密钥删除
     *
     * @param appId 服务ID
     * @return DataResponse
     */
    @DeleteMapping("/secretKey")
    @ApiOperation(value = "密钥删除")
    public <T> DataResponse<T> deleteSecretKey(@Valid @RequestParam @NotNull(message = "32000006") Integer appId) {
        int result = tokenService.deleteSecretKey(appId);
        if (result > 0) {
            log.info("密钥删除成功");
            return DataResponse.success();
        } else {
            log.error("密钥删除失败");
            throw new CommonException(ErrorCode.DELETE_ERROR);
        }
    }

    /**
     * 第三方服务Token获取
     *
     * @param appKey 密钥
     * @return DataResponse
     * @throws Exception CommonException
     */
    @PostMapping("/openapi/token")
    @ApiOperation(value = "第三方服务Token获取")
    public DataResponse<Map<String, Object>> openApiToken(@Valid @RequestParam @NotBlank(message = "32000006") String appKey) throws Exception {
        OpenApiTokenInfo info = tokenService.selectTokenInfo(appKey);
        if (Objects.isNull(info)) {
            log.error("数据库第三方服务信息获取失败");
            throw new CommonException(ErrorCode.SELECT_ERROR);
        }
        String token = createOpenApiToken(info);
        String jsonToken = "{'appKey':'" + info.getAppKey() + "', 'appSecret':'" + info.getAppSecret() + "', 'token':'" + token + "'}";
        jsonToken = AesUtils.encrypt(jsonToken);
        log.info("{} Token返回成功", info.getAppId());
        Map<String, Object> data = new HashMap<>();
        data.put("token", jsonToken);
        return DataResponse.of(data);
    }
}