package com.zte.msg.pushcenter.pccore.controller;

import com.zte.msg.pushcenter.pccore.dto.DataResponse;
import com.zte.msg.pushcenter.pccore.dto.PageResponse;
import com.zte.msg.pushcenter.pccore.dto.req.RoleReqDTO;
import com.zte.msg.pushcenter.pccore.dto.res.RoleResDTO;
import com.zte.msg.pushcenter.pccore.entity.Role;
import com.zte.msg.pushcenter.pccore.service.RoleService;
import com.zte.msg.pushcenter.pccore.utils.PermissionCheck;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description:
 *
 * @author frp
 * @version 1.0
 * @date 2020/12/28 14:42
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/role")
@Api(tags = "角色管理")
@Validated
public class RoleController {

    @Resource
    private RoleService roleService;

    /**
     * 获取所有角色信息
     *
     * @return List<Role>
     */
    @PermissionCheck(permissionName = {"system:role:list"})
    @GetMapping
    @ApiOperation(value = "获取所有角色信息")
    public DataResponse<List<Role>> listAllRole() {
        return DataResponse.of(roleService.listAllRole());
    }

    /**
     * 分页获取角色信息
     *
     * @param roleReqDTO 角色分页信息
     * @return PageInfo<Role>
     */
    @PermissionCheck(permissionName = {"system:role:list"})
    @PostMapping("/list")
    @ApiOperation(value = "分页获取角色信息")
    public PageResponse<Role> listRole(@Valid @RequestBody RoleReqDTO roleReqDTO) {
        return PageResponse.of(roleService.listRole(roleReqDTO));
    }

    /**
     * 获取角色对应菜单id
     *
     * @param roleId 角色id
     * @return Map<String, Object>
     */
    @PermissionCheck(permissionName = {"system:role:list"})
    @GetMapping("/{roleId}")
    @ApiOperation(value = "获取角色对应菜单id")
    public DataResponse<Map<String, Object>> detailRoleMenu(@PathVariable Long roleId) {
        Map<String, Object> data = new HashMap<>(16);
        data.put("menuIds", roleService.selectMenuIds(roleId));
        return DataResponse.of(data);
    }

    /**
     * 删除角色
     *
     * @param ids 角色信息id
     * @return <T>
     */
    @PermissionCheck(permissionName = {"system:role:remove"})
    @DeleteMapping
    @ApiOperation(value = "删除角色")
    public <T> DataResponse<T> deleteRole(@Valid @RequestBody List<Long> ids) {
        roleService.deleteRole(ids);
        return DataResponse.success();
    }

    /**
     * 新增角色
     *
     * @param roleReqDTO 新增角色信息
     * @return <T>
     */
    @PermissionCheck(permissionName = {"system:role:add"})
    @PutMapping
    @ApiOperation(value = "新增角色")
    public <T> DataResponse<T> insertRole(@Valid @RequestBody RoleReqDTO roleReqDTO){
        roleService.insertRole(roleReqDTO);
        return DataResponse.success();
    }

    /**
     * 修改角色
     *
     * @param roleReqDTO 修改角色信息
     * @return <T>
     */
    @PermissionCheck(permissionName = {"system:role:modify"})
    @PostMapping
    @ApiOperation(value = "修改角色")
    public <T> DataResponse<T> updateRole(@Valid @RequestBody RoleReqDTO roleReqDTO){
        roleService.updateRole(roleReqDTO);
        return DataResponse.success();
    }
}