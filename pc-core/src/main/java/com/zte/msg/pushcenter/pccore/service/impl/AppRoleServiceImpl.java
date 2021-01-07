package com.zte.msg.pushcenter.pccore.service.impl;

import com.zte.msg.pushcenter.pccore.dto.res.AppRoleResDTO;
import com.zte.msg.pushcenter.pccore.entity.AppRole;
import com.zte.msg.pushcenter.pccore.entity.SendMode;
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
                List<AppRole> appRole = appRoleMapper.selectAppMode(appRoleResDTO.getAppId());
                for (AppRole appRoleTemplate : appRole){
                    appRoleTemplate.setTemplate(appRoleMapper.selectTemplate(appRoleTemplate.getModeId()));
                }
                appRoleResDTO.setSendMode(appRole);
            }
            return appRoleResDTOList;
        } else {
            throw new CommonException(ErrorCode.SELECT_EMPTY);
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
    public List<SendMode> listSendMode() {
        List<SendMode> sendModeList = appRoleMapper.listSendMode();
        if (null != sendModeList && !sendModeList.isEmpty()) {
            log.info("权限列表获取成功");
            return sendModeList;
        } else {
            log.error("权限列表获取失败");
            throw new CommonException(ErrorCode.SELECT_EMPTY);
        }
    }

    @Override
    public void deleteSendMode(Integer modeId) {
        if (Objects.isNull(modeId)) {
            log.error("权限ID返回为空");
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        int result = appRoleMapper.deleteSendMode(modeId);
        if (result > 0) {
            log.info("权限删除成功");
        } else {
            log.error("权限删除失败");
            throw new CommonException(ErrorCode.DELETE_ERROR);
        }
    }

    @Override
    public void updateSendMode(SendMode sendMode) {
        if (Objects.isNull(sendMode)) {
            log.error("权限信息返回为空");
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        int result = appRoleMapper.updateSendMode(sendMode);
        if (result > 0) {
            log.info("权限修改成功");
        } else {
            log.error("权限修改失败");
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }
    }

    @Override
    public void insertSendMode(String modeName) {
        if (StringUtils.isBlank(modeName)) {
            log.error("权限信息返回为空");
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        Integer id = appRoleMapper.selectSendModeId(modeName);
        if (Objects.nonNull(id)) {
            log.error("权限已存在");
            throw new CommonException(ErrorCode.DATA_EXIST);
        }
        int result = appRoleMapper.insertSendMode(modeName);
        if (result > 0) {
            log.info("添加权限成功");
        } else {
            log.error("添加权限失败");
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }
    }
}