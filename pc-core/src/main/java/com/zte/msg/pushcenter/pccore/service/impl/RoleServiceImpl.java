package com.zte.msg.pushcenter.pccore.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zte.msg.pushcenter.pccore.dto.req.RoleReqDTO;
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
        if (null == roleReqDTO.getPage() || null == roleReqDTO.getSize()) {
            throw new CommonException(ErrorCode.PAGE_PARAM_EMPTY);
        }
        if (0 >= roleReqDTO.getPage() || 0 >= roleReqDTO.getSize()) {
            throw new CommonException(ErrorCode.PAGE_PARAM_ERROR);
        }
        PageHelper.startPage(roleReqDTO.getPage().intValue(), roleReqDTO.getSize().intValue());
        List<Role> list = roleMapper.listRole(roleReqDTO);
        return new PageInfo<>(list);
    }

    @Override
    public void deleteRole(List<Integer> ids) {
        if (null == ids || ids.isEmpty()) {
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        int result = roleMapper.deleteRole(ids);
        if (result > 0) {
            log.info("角色删除成功");
        } else {
            throw new CommonException(ErrorCode.DELETE_ERROR);
        }
    }

    @Override
    public void insertRole(Role role) {
        if (Objects.isNull(role)) {
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        int result = roleMapper.insertRole(role, TokenUtil.getCurrentUserName());
        if (result > 0) {
            log.info("新增角色成功");
        } else {
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }
    }

    @Override
    public void updateRole(Role role) {
        if (Objects.isNull(role)) {
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        role.setUpdatedBy(TokenUtil.getCurrentUserName());
        int result = roleMapper.updateRole(role);
        if (result > 0) {
            log.info("{}修改角色成功", role.getId());
        } else {
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }
    }
}
