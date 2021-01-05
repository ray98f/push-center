package com.zte.msg.pushcenter.pccore.service.impl;

import com.zte.msg.pushcenter.pccore.dto.res.AppRoleResDTO;
import com.zte.msg.pushcenter.pccore.entity.AppRole;
import com.zte.msg.pushcenter.pccore.entity.Role;
import com.zte.msg.pushcenter.pccore.enums.ErrorCode;
import com.zte.msg.pushcenter.pccore.exception.CommonException;
import com.zte.msg.pushcenter.pccore.mapper.AppRoleMapper;
import com.zte.msg.pushcenter.pccore.service.AppRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
        List<AppRoleResDTO> appRoleResDTOList = appRoleMapper.listApp();
        if (null != appRoleResDTOList && !appRoleResDTOList.isEmpty()) {
            log.info("服务列表查询成功");
            for (AppRoleResDTO appRoleResDTO : appRoleResDTOList) {
                List<AppRole> appRole = appRoleMapper.selectAppRole(appRoleResDTO.getAppId());
                for (AppRole appRoleTemplate : appRole){
                    appRoleTemplate.setTemplate(appRoleMapper.selectTemplate(appRoleTemplate.getRoleId()));
                }
                appRoleResDTO.setRole(appRole);
            }
            return appRoleResDTOList;
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

    @Override
    public List<Role> listRole() {
        List<Role> roleList = appRoleMapper.listRole();
        if (null != roleList && !roleList.isEmpty()) {
            log.info("权限列表获取成功");
            return roleList;
        } else {
            log.error("权限列表获取失败");
            throw new CommonException(ErrorCode.SELECT_EMPTY);
        }
    }

    @Override
    public void deleteRole(Integer roleId) {
        if (Objects.isNull(roleId)) {
            log.error("权限ID返回为空");
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        int result = appRoleMapper.deleteRole(roleId);
        if (result > 0) {
            log.info("权限删除成功");
        } else {
            log.error("权限删除失败");
            throw new CommonException(ErrorCode.DELETE_ERROR);
        }
    }

    @Override
    public void updateRole(Role role) {
        if (Objects.isNull(role)) {
            log.error("权限信息返回为空");
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        int result = appRoleMapper.updateRole(role);
        if (result > 0) {
            log.info("权限修改成功");
        } else {
            log.error("权限修改失败");
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }
    }

    @Override
    public void insertRole(String roleName) {
        if (StringUtils.isBlank(roleName)) {
            log.error("权限信息返回为空");
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        Integer id = appRoleMapper.selectRoleId(roleName);
        if (Objects.nonNull(id)) {
            log.error("权限已存在");
            throw new CommonException(ErrorCode.DATA_EXIST);
        }
        int result = appRoleMapper.insertRole(roleName);
        if (result > 0) {
            log.info("添加权限成功");
        } else {
            log.error("添加权限失败");
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }
    }
}