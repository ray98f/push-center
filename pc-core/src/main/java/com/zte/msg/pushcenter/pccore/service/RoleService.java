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

    List<Role> listAllRole();

    PageInfo<Role> listRole(RoleReqDTO roleReqDTO);

    void deleteRole(List<Long> ids);

    void insertRole(RoleReqDTO role);

    void updateRole(RoleReqDTO role);

    List<Long> selectMenuIds(Long roleId);
}
