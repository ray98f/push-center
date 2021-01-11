package com.zte.msg.pushcenter.pccore.service;


import com.zte.msg.pushcenter.pccore.dto.res.AppRoleResDTO;
import com.zte.msg.pushcenter.pccore.entity.SendMode;

import java.util.List;

/**
 * description:
 *
 * @author frp
 * @version 1.0
 * @date 2020/12/29 10:40
 */
public interface AppRoleService {

    AppRoleResDTO listAppRole(Integer appId);

    void editAppRole(List<AppRoleResDTO> appRoleResDTOList);

    List<SendMode> listSendMode();

    void deleteSendMode(Integer modeId);

    void updateSendMode(SendMode sendMode);

    void insertSendMode(String modeName);
}
