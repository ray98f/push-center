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

    List<Role> listAllRole();

    List<Role> listRole(RoleReqDTO roleReqDTO);

    int deleteRole(List<Long> ids);

    int deleteRoleMenu(List<Long> ids);

    int insertRole(RoleReqDTO role);

    int insertRoleMenu(Long roleId, List<Long> menuIds, String doName);

    int updateRole(RoleReqDTO role);

    void deleteRoleMenus(Long roleId);

    List<String> selectMenuRoles(Long roleId);

    List<Long> selectMenuIds(Long roleId);
}
