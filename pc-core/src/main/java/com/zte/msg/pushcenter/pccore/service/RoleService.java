package com.zte.msg.pushcenter.pccore.service;

import com.github.pagehelper.PageInfo;
import com.zte.msg.pushcenter.pccore.dto.req.RoleReqDTO;
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

    void deleteRole(List<Integer> ids);

    void insertRole(Role role);

    void updateRole(Role role);
}
