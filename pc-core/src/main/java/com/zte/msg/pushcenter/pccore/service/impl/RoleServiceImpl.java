package com.zte.msg.pushcenter.pccore.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zte.msg.pushcenter.pccore.dto.req.RoleReqDTO;
import com.zte.msg.pushcenter.pccore.dto.res.RoleResDTO;
import com.zte.msg.pushcenter.pccore.entity.Role;
import com.zte.msg.pushcenter.pccore.enums.ErrorCode;
import com.zte.msg.pushcenter.pccore.exception.CommonException;
import com.zte.msg.pushcenter.pccore.mapper.RoleMapper;
import com.zte.msg.pushcenter.pccore.service.RoleService;
import com.zte.msg.pushcenter.pccore.utils.TokenUtil;
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
 * @date 2021/1/18 10:26
 */
@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> listAllRole() {
        List<Role> list = roleMapper.listAllRole();
        if (null == list || list.isEmpty()) {
            throw new CommonException(ErrorCode.RESOURCE_NOT_EXIST);
        }
        return list;
    }

    @Override
    public PageInfo<Role> listRole(RoleReqDTO roleReqDTO) {
        if (Objects.isNull(roleReqDTO)) {
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        PageHelper.startPage(roleReqDTO.getPage().intValue(), roleReqDTO.getSize().intValue());
        List<Role> list = roleMapper.listRole(roleReqDTO);
        return new PageInfo<>(list);
    }

    @Override
    public void deleteRole(List<Long> ids) {
        if (null == ids || ids.isEmpty()) {
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        int deleteRole = roleMapper.deleteRole(ids);
        int deleteRoleMenu = roleMapper.deleteRoleMenu(ids);
        if (deleteRole >= 0 && deleteRoleMenu >= 0) {
            log.info("角色删除成功(角色相关权限已删除)");
        } else {
            throw new CommonException(ErrorCode.DELETE_ERROR);
        }
    }

    @Override
    public void insertRole(RoleReqDTO role) {
        if (Objects.isNull(role)) {
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        role.setCreatedBy(TokenUtil.getCurrentUserName());
        int insertRole = roleMapper.insertRole(role);
        if (insertRole <= 0) {
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }
        if (null == role.getMenuIds() || role.getMenuIds().isEmpty()) {
            log.warn("没有需要添加的角色权限信息");
            return;
        }
        int insertRoleMenu = roleMapper.insertRoleMenu(role.getId(), role.getMenuIds(), role.getCreatedBy());
        if (insertRoleMenu <= 0) {
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }
        log.info("新增角色成功");
    }

    @Override
    public void updateRole(RoleReqDTO role) {
        if (Objects.isNull(role)) {
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        role.setCreatedBy(TokenUtil.getCurrentUserName());
        int updateRole = roleMapper.updateRole(role);
        if (updateRole <= 0) {
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }
        roleMapper.deleteRoleMenus(role.getId());
        if (null == role.getMenuIds() || role.getMenuIds().isEmpty()) {
            log.warn("没有需要修改的角色权限信息");
            return;
        }
        int insertRoleMenu = roleMapper.insertRoleMenu(role.getId(), role.getMenuIds(), role.getCreatedBy());
        if (insertRoleMenu <= 0) {
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }
        log.info("修改角色成功");
    }

    @Override
    public List<Long> selectMenuIds(Long roleId) {
        if (null == roleId || roleId < 0) {
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        List<Long> list = roleMapper.selectMenuIds(roleId);
        if (null == list || list.isEmpty()) {
            throw new CommonException(ErrorCode.SELECT_ERROR);
        } else {
            log.info("角色菜单权限返回成功");
            return list;
        }
    }
}
