package com.zte.msg.pushcenter.service.impl;

import com.zte.msg.pushcenter.dto.res.AppRoleResDTO;
import com.zte.msg.pushcenter.enums.ErrorCode;
import com.zte.msg.pushcenter.exception.CommonException;
import com.zte.msg.pushcenter.mapper.AppRoleMapper;
import com.zte.msg.pushcenter.service.AppRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * description:
 *
 * @author frp
 * @version 1.0
 * @date 2020/12/29 10:19
 */
@Service
@Slf4j
public class AppRoleServiceImpl implements AppRoleService {

    @Autowired
    private AppRoleMapper appRoleMapper;

    @Override
    public List<AppRoleResDTO> listAppRole() {
        List<AppRoleResDTO> appRole = appRoleMapper.listApp();
        if (null != appRole && !appRole.isEmpty()) {
            log.info("服务列表查询成功");
            for (AppRoleResDTO appRoleResDTO : appRole) {
                appRoleResDTO.setRole(appRoleMapper.selectAppRole(appRoleResDTO.getAppId()));
            }
            return appRole;
        } else {
            log.error("服务列表查询失败");
            throw new CommonException(ErrorCode.SELECT_EMPTY);
        }
    }

    @Override
    public AppRoleResDTO selectAppRole(Integer appId) {
        AppRoleResDTO appRoleResDTO = appRoleMapper.selectApp(appId);
        if (!Objects.isNull(appRoleResDTO)) {
            log.info("app {} 权限查询成功", appId);
            appRoleResDTO.setRole(appRoleMapper.selectAppRole(appId));
            return appRoleResDTO;
        } else {
            log.error("app {} 权限查询失败", appId);
            throw new CommonException(ErrorCode.SELECT_ERROR);
        }
    }

    @Override
    public void editAppRole(List<AppRoleResDTO> appRoleResDTOList) {
        if (null == appRoleResDTOList || appRoleResDTOList.isEmpty()) {
            log.error("app权限修改参数返回为空");
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        int result = appRoleMapper.editAppRole(appRoleResDTOList);
        if (result > 0) {
            log.info("编辑app权限成功");
        } else {
            log.error("编辑app权限失败");
            throw new CommonException(ErrorCode.APP_ROLE_EDIT_ERROR);
        }
    }
}