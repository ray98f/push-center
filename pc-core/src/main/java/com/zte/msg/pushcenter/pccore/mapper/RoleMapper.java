package com.zte.msg.pushcenter.pccore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zte.msg.pushcenter.pccore.dto.req.RoleReqDTO;
import com.zte.msg.pushcenter.pccore.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author frp
 */
@Mapper
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 获取所有角色
     * @return
     */
    List<Role> listAllRole();

    /**
     * 查询角色列表
     * @param roleReqDTO
     * @return
     */
    List<Role> listRole(RoleReqDTO roleReqDTO);

    /**
     * 删除角色
     * @param ids
     * @return
     */
    int deleteRole(List<Long> ids);

    /**
     * 删除角色菜单关联
     * @param ids
     * @return
     */
    int deleteRoleMenu(List<Long> ids);

    /**
     * 新增角色
     * @param role
     * @return
     */
    int insertRole(RoleReqDTO role);

    /**
     * 新增角色菜单关联
     * @param roleId
     * @param menuIds
     * @param doName
     * @return
     */
    int insertRoleMenu(Long roleId, List<Long> menuIds, String doName);

    /**
     * 修改角色
     * @param role
     * @return
     */
    int updateRole(RoleReqDTO role);

    /**
     * 清空角色修改关联
     * @param roleId
     */
    void deleteRoleMenus(Long roleId);

    /**
     * 搜索角色对应菜单权限标识
     * @param roleId
     * @return
     */
    List<String> selectMenuRoles(Long roleId);

    /**
     * 获取角色对应菜单ids
     * @param roleId
     * @return
     */
    List<Long> selectRoleMenuIds(Long roleId);

    /**
     * 搜索角色对应菜单ids
     * @param roleIds
     * @return
     */
    List<Long> selectMenuIds(List<Long> roleIds);
}
