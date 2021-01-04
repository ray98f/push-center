package com.zte.msg.pushcenter.pccore.service;


import com.zte.msg.pushcenter.pccore.dto.res.AppRoleResDTO;
import com.zte.msg.pushcenter.pccore.entity.Role;

import java.util.List;

/**
 * description:
 *
 * @author frp
 * @version 1.0
 * @date 2020/12/29 10:40
 */
public interface AppRoleService {

    List<AppRoleResDTO> listAppRole();

    AppRoleResDTO selectAppRole(Integer appId);

    void editAppRole(List<AppRoleResDTO> appRoleResDTOList);

    List<Role> listRole();

    void deleteRole(Integer roleId);

    void updateRole(Role role);

    void insertRole(String roleName);
}
