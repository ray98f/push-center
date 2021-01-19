package com.zte.msg.pushcenter.pccore.controller;

import com.zte.msg.pushcenter.pccore.dto.DataResponse;
import com.zte.msg.pushcenter.pccore.dto.PageResponse;
import com.zte.msg.pushcenter.pccore.dto.req.RoleReqDTO;
import com.zte.msg.pushcenter.pccore.entity.Role;
import com.zte.msg.pushcenter.pccore.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

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
    @PostMapping("/list")
    @ApiOperation(value = "分页获取角色信息")
    public PageResponse<Role> listAllRole(@Valid @RequestBody RoleReqDTO roleReqDTO) {
        return PageResponse.of(roleService.listRole(roleReqDTO));
    }

    /**
     * 删除角色
     *
     * @param ids 角色信息id
     * @return <T>
     */
    @DeleteMapping
    @ApiOperation(value = "删除角色")
    public <T> DataResponse<T> deleteRole(@Valid @RequestBody List<Integer> ids) {
        roleService.deleteRole(ids);
        return DataResponse.success();
    }

    /**
     * 新增角色
     *
     * @param role 新增角色信息
     * @return <T>
     */
    @PutMapping
    @ApiOperation(value = "新增角色")
    public <T> DataResponse<T> insertRole(@Valid @RequestBody Role role){
        roleService.insertRole(role);
        return DataResponse.success();
    }

    /**
     * 修改角色
     *
     * @param role 修改角色信息
     * @return <T>
     */
    @PostMapping
    @ApiOperation(value = "修改角色")
    public <T> DataResponse<T> updateRole(@Valid @RequestBody Role role){
        roleService.insertRole(role);
        return DataResponse.success();
    }
}