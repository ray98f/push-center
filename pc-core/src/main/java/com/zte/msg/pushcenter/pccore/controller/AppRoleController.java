package com.zte.msg.pushcenter.pccore.controller;

import com.zte.msg.pushcenter.pccore.dto.DataResponse;
import com.zte.msg.pushcenter.pccore.dto.res.AppRoleResDTO;
import com.zte.msg.pushcenter.pccore.entity.SendMode;
import com.zte.msg.pushcenter.pccore.service.AppRoleService;
import com.zte.msg.pushcenter.pccore.utils.PermissionCheck;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * description:
 *
 * @author frp
 * @version 1.0
 * @date 2020/12/29 10:37
 */
@Slf4j
@RestController
@RequestMapping("/api/v1")
@Api(tags = "服务权限管理")
@Validated
public class AppRoleController {

    @Resource
    private AppRoleService appRoleService;

    /**
     * 第三方服务权限列表获取
     *
     * @return List<AppRoleResDTO>
     */
    @PermissionCheck(permissionName = {"application:config:role"})
    @GetMapping("/appRole")
    @ApiOperation(value = "第三方服务权限列表获取")
    public DataResponse<AppRoleResDTO> listAppRole(@Valid @RequestParam @NotNull(message = "32000006") Integer appId) {
        return DataResponse.of(appRoleService.listAppRole(appId));
    }

    /**
     * 编辑第三方服务权限（有则修改，无则新增）
     *
     * @param appRoleResDTO 第三方服务编辑参数
     * @return <T>
     */
    @PermissionCheck(permissionName = {"application:config:role"})
    @PostMapping("/appRole")
    @ApiOperation(value = "编辑第三方服务权限")
    public <T> DataResponse<T> editAppRole(@RequestBody @Valid AppRoleResDTO appRoleResDTO){
        appRoleService.editAppRole(appRoleResDTO);
        return DataResponse.success();
    }

    /**
     * 发送方式列表获取
     *
     * @return List<SendMode>
     */
    @GetMapping("/sendMode")
    @ApiOperation(value = "权限列表获取")
    public DataResponse<List<SendMode>> listSendMode(){
        return DataResponse.of(appRoleService.listSendMode());
    }

    /**
     * 权限删除
     *
     * @param modeId 发送方式ID
     * @return <T>
     */
    @DeleteMapping("/sendMode")
    @ApiOperation(value = "权限删除")
    public <T> DataResponse<T> deleteSendMode(@Valid @RequestParam @NotNull(message = "32000006") Integer modeId){
        appRoleService.deleteSendMode(modeId);
        return DataResponse.success();
    }

    /**
     * 发送方式修改
     *
     * @param sendMode 发送方式信息
     * @return <T>
     */
    @PostMapping("/sendMode")
    @ApiOperation(value = "权限修改")
    public <T> DataResponse<T> updateSendMode(@RequestBody @Valid SendMode sendMode){
        appRoleService.updateSendMode(sendMode);
        return DataResponse.success();
    }

    /**
     * 发送方式权限
     *
     * @param modeName 权限名称
     * @return <T>
     */
    @PutMapping("/sendMode")
    @ApiOperation(value = "添加权限")
    public <T> DataResponse<T> insertSendMode(@Valid @RequestParam @NotNull(message = "32000006") String modeName){
        appRoleService.insertSendMode(modeName);
        return DataResponse.success();
    }
}