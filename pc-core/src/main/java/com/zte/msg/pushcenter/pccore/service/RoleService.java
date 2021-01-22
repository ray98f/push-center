package com.zte.msg.pushcenter.pccore.service;

import com.github.pagehelper.PageInfo;
import com.zte.msg.pushcenter.pccore.dto.req.RoleReqDTO;
import com.zte.msg.pushcenter.pccore.dto.res.RoleResDTO;
import com.zte.msg.pushcenter.pccore.entity.Role;

import java.util.List;

/**
 * description:
 *
 * @author frp
 * @version 1.0
 * @date 2020/12/23 15:42
 */
public interface RoleService {

    /**
     * 获取角色列表
     * @return
     */
    List<Role> listAllRole();

    /**
     * 查询角色列表
     * @param roleReqDTO
     * @return
     */
    PageInfo<Role> listRole(RoleReqDTO roleReqDTO);

    /**
     * 删除角色
     * @param ids
     */
    void deleteRole(List<Long> ids);

    /**
     * 新增角色
     * @param role
     */
    void insertRole(RoleReqDTO role);

    /**
     * 修改角色
     * @param role
     */
    void updateRole(RoleReqDTO role);

    /**
     * 获取角色对应菜单ids
     * @param roleId
     * @return
     */
    List<Long> selectMenuIds(Long roleId);
}
