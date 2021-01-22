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

    /**
     * 获取应用权限列表
     * @param appId
     * @return
     */
    AppRoleResDTO listAppRole(Integer appId);

    /**
     * 修改应用权限
     * @param appRoleResDTO
     */
    void editAppRole(AppRoleResDTO appRoleResDTO);

    /**
     * 获取消息推送方式列表
     * @return
     */
    List<SendMode> listSendMode();

    /**
     * 删除消息推送方式
     * @param modeId
     */
    void deleteSendMode(Integer modeId);

    /**
     * 修改消息推送方式
     * @param sendMode
     */
    void updateSendMode(SendMode sendMode);

    /**
     * 新增消息推送方式
     * @param modeName
     */
    void insertSendMode(String modeName);
}
