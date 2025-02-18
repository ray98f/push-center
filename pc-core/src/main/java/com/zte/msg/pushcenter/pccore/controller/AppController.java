package com.zte.msg.pushcenter.pccore.controller;

import com.zte.msg.pushcenter.pccore.dto.DataResponse;
import com.zte.msg.pushcenter.pccore.dto.PageResponse;
import com.zte.msg.pushcenter.pccore.dto.req.AppListReqDTO;
import com.zte.msg.pushcenter.pccore.dto.res.SecretKeyResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.WhiteIpResDTO;
import com.zte.msg.pushcenter.pccore.entity.App;
import com.zte.msg.pushcenter.pccore.service.AppService;
import com.zte.msg.pushcenter.pccore.service.SecretService;
import com.zte.msg.pushcenter.pccore.annotation.PermissionCheck;
import com.zte.msg.pushcenter.pccore.utils.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description:
 *
 * @author frp
 * @version 1.0
 * @date 2020/12/30 17:03
 */
@Slf4j
@RestController
@RequestMapping("/api/v1")
@Api(tags = "服务管理")
@Validated
public class AppController {

    @Resource
    private AppService appService;

    @Resource
    private SecretService secretService;

    /**
     * 获取所有应用列表
     *
     * @return List<App>
     */
    @GetMapping("/app")
    @ApiOperation(value = "获取所有应用列表")
    public DataResponse<List<App>> listAllApp() {
        return DataResponse.of(appService.listAllApp());
    }

    /**
     * 服务列表获取
     *
     * @return List<App>
     */
    @PermissionCheck(permissionName = {"application:config:list"})
    @PostMapping("/app/list")
    @ApiOperation(value = "服务列表获取")
    public PageResponse<App> listApp(@Valid @RequestBody AppListReqDTO appListReqDTO) {
        return PageResponse.of(appService.listApp(appListReqDTO));
    }

    /**
     * 服务删除
     *
     * @param appIds 服务ID
     * @return <T>
     */
    @PermissionCheck(permissionName = {"application:config:remove"})
    @DeleteMapping("/app")
    @ApiOperation(value = "服务删除")
    public <T> DataResponse<T> deleteApp(@Valid @RequestBody List<Integer> appIds) {
        String userName = TokenUtil.getCurrentUserName();
        appService.deleteApp(appIds, userName);
        return DataResponse.success();
    }

    /**
     * 服务修改
     *
     * @param app 服务信息
     * @return <T>
     */
    @PermissionCheck(permissionName = {"application:config:modify"})
    @PostMapping("/app")
    @ApiOperation(value = "服务修改")
    public <T> DataResponse<T> updateApp(@RequestBody @Valid App app) {
        app.setUpdatedBy(TokenUtil.getCurrentUserName());
        appService.updateApp(app);
        return DataResponse.success();
    }

    /**
     * 新增服务
     *
     * @param app 服务详情
     * @return <T>
     */
    @PermissionCheck(permissionName = {"application:config:add"})
    @PutMapping("/app")
    @ApiOperation(value = "新增服务")
    public <T> DataResponse<T> insertApp(@Valid @RequestBody App app) {
        String userName = TokenUtil.getCurrentUserName();
        app.setCreatedBy(userName);
        app.setUpdatedBy(userName);
        appService.insertApp(app);
        return DataResponse.success();
    }

    /**
     * 重置密钥
     *
     * @return <T>
     */
    @PermissionCheck(permissionName = {"application:config:reset-key"})
    @GetMapping("/app/reset")
    @ApiOperation(value = "重置密钥")
    public <T> DataResponse<T> resetKey(@Valid @RequestParam @NotNull(message = "32000006") Integer appId) {
        String userName = TokenUtil.getCurrentUserName();
        appService.resetKey(userName, appId);
        return DataResponse.success();
    }

    /**
     * 根据应用Id获取应用密钥
     *
     * @return Map<String, Object>
     */
    @GetMapping("/app/secret")
    @ApiOperation(value = "根据应用Id获取应用密钥")
    public DataResponse<SecretKeyResDTO> selectAppSecret(@Valid @RequestParam @NotNull(message = "32000006") Integer appId){
        return DataResponse.of(secretService.selectSecretKey(appId));
    }

    /**
     * 根据appId获取secret
     * @param appId
     * @return
     */
    @GetMapping("/open/app/secret")
    @ApiOperation(value = "根据appId获取secret")
    public DataResponse<Map<String, Object>> getSecret(@Valid @RequestParam @NotNull(message = "32000006") Long appId){
        String secret = secretService.selectAppSecret(appId);
        Map<String, Object> data = new HashMap<>(16);
        data.put("secret", secret);
        return DataResponse.of(data);
    }

    @GetMapping("/app/white/ip")
    @ApiOperation(value = "根据appId获取secret")
    public DataResponse<List<WhiteIpResDTO>> getWhiteIp(){
        return DataResponse.of(appService.listWhiteIp());
    }
}
