package com.zte.msg.pushcenter.service;

import com.zte.msg.pushcenter.dto.res.AppRoleResDTO;

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
}
