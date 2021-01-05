package com.zte.msg.pushcenter.pccore.controller;

import com.zte.msg.pushcenter.pccore.dto.DataResponse;
import com.zte.msg.pushcenter.pccore.dto.res.AppRoleResDTO;
import com.zte.msg.pushcenter.pccore.entity.Role;
import com.zte.msg.pushcenter.pccore.service.AppRoleService;
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
@Api(tags = "第三方服务权限管理")
@Validated
public class AppRoleController {

    @Resource
    private AppRoleService appRoleService;

    /**
     * 第三方服务权限列表获取
     *
     * @return List<AppRoleResDTO>
     */
    @GetMapping("/appRole")
    @ApiOperation(value = "第三方服务权限列表获取")
    public DataResponse<List<AppRoleResDTO>> listAppRole() {
        return DataResponse.of(appRoleService.listAppRole());
    }

    /**
     * 获取第三方服务权限详情
     *
     * @param appId appID
     * @return AppRoleResDTO
     */
    @PutMapping("/appRole")
    @ApiOperation(value = "获取第三方服务权限详情")
    public DataResponse<AppRoleResDTO> getAppRole(@Valid @RequestParam @NotNull(message = "32000006") Integer appId) {
        return DataResponse.of(appRoleService.selectAppRole(appId));
    }

    /**
     * 编辑第三方服务权限（有则修改，无则新增）
     *
     * @param appRoleResDTOList 第三方服务编辑参数
     * @return <T>
     */
    @PostMapping("/appRole")
    @ApiOperation(value = "编辑第三方服务权限")
    public <T> DataResponse<T> editAppRole(@RequestBody @Valid List<AppRoleResDTO> appRoleResDTOList){
        appRoleService.editAppRole(appRoleResDTOList);
        return DataResponse.success();
    }

    /**
     * 权限列表获取
     *
     * @return List<Role>
     */
    @GetMapping("/role")
    @ApiOperation(value = "权限列表获取")
    public DataResponse<List<Role>> listRole(){
        return DataResponse.of(appRoleService.listRole());
    }

    /**
     * 权限删除
     *
     * @param roleId 权限ID
     * @return <T>
     */
    @DeleteMapping("/role")
    @ApiOperation(value = "权限删除")
    public <T> DataResponse<T> deleteRole(@Valid @RequestParam @NotNull(message = "32000006") Integer roleId){
        appRoleService.deleteRole(roleId);
        return DataResponse.success();
    }

    /**
     * 权限修改
     *
     * @param role 权限信息
     * @return <T>
     */
    @PostMapping("/role")
    @ApiOperation(value = "权限修改")
    public <T> DataResponse<T> updateRole(@RequestBody @Valid Role role){
        appRoleService.updateRole(role);
        return DataResponse.success();
    }

    /**
     * 添加权限
     *
     * @param roleName 权限名称
     * @return <T>
     */
    @PutMapping("/role")
    @ApiOperation(value = "添加权限")
    public <T> DataResponse<T> insertRole(@Valid @RequestParam @NotNull(message = "32000006") String roleName){
        appRoleService.insertRole(roleName);
        return DataResponse.success();
    }
}