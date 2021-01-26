package com.zte.msg.pushcenter.pccore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zte.msg.pushcenter.pccore.dto.PageReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.PasswordReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.UserReqDTO;
import com.zte.msg.pushcenter.pccore.entity.User;
import com.zte.msg.pushcenter.pccore.enums.ErrorCode;
import com.zte.msg.pushcenter.pccore.exception.CommonException;
import com.zte.msg.pushcenter.pccore.mapper.UserMapper;
import com.zte.msg.pushcenter.pccore.service.UserService;
import com.zte.msg.pushcenter.pccore.utils.AesUtils;
import com.zte.msg.pushcenter.pccore.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * description:
 *
 * @author frp
 * @version 1.0
 * @date 2020/12/28 15:42
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    public static final String DECRYPTED_DATA = "123456";

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectUserInfo(User user) {
        if (Objects.isNull(user)) {
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        User userInfo = userMapper.selectUserInfo(user.getUserName());
        if (Objects.isNull(userInfo)) {
            throw new CommonException(ErrorCode.USER_NOT_EXIST);
        }
        if (!user.getPassword().equals(AesUtils.decrypt(userInfo.getPassword()))) {
            throw new CommonException(ErrorCode.LOGIN_PASSWORD_ERROR);
        }
        log.info("用户搜索成功");
        return userInfo;
    }

    @Override
    public void insertUser(User user) {
        if (Objects.isNull(user)) {
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        User userInfo = userMapper.selectUserInfo(user.getUserName());
        if (!Objects.isNull(userInfo) && !Objects.isNull(userInfo.getId())) {
            throw new CommonException(ErrorCode.DATA_EXIST);
        }
        String password = AesUtils.encrypt(user.getPassword());
        user.setPassword(password);
        int result = userMapper.insertUser(user, TokenUtil.getCurrentUserName());
        if (result > 0) {
            log.info("用户新增成功");
        } else {
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }
    }

    @Override
    public void changePwd(PasswordReqDTO passwordReqDTO) {
        if (Objects.isNull(passwordReqDTO)) {
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        passwordReqDTO.setOldPwd(AesUtils.encrypt(passwordReqDTO.getOldPwd()));
        passwordReqDTO.setNewPwd(AesUtils.encrypt(passwordReqDTO.getNewPwd()));
        int result = userMapper.changePwd(passwordReqDTO, TokenUtil.getCurrentUserName());
        if (result > 0) {
            log.info("用户密码修改成功");
        } else {
            throw new CommonException(ErrorCode.USER_PWD_CHANGE_FAIL);
        }
    }

    @Override
    public void resetPwd(Integer id) {
        int result = userMapper.resetPwd(AesUtils.encrypt(DECRYPTED_DATA), TokenUtil.getCurrentUserName(), id);
        if (result > 0) {
            log.info("用户密码重置成功");
        } else {
            throw new CommonException(ErrorCode.USER_PWD_CHANGE_FAIL);
        }
    }

    @Override
    public void editUser(UserReqDTO userReqDTO) {
        if (Objects.isNull(userReqDTO)) {
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        int result = userMapper.editUser(userReqDTO, TokenUtil.getCurrentUserName());
        if (result > 0) {
            log.info("修改用户信息成功");
        } else {
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }
    }

    @Override
    public void deleteUser(List<Integer> ids) {
        if (null == ids || ids.isEmpty()) {
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        int result = userMapper.deleteUser(ids);
        if (result > 0) {
            log.info("用户删除成功");
        } else {
            throw new CommonException(ErrorCode.DELETE_ERROR);
        }
    }

    @Override
    public List<User> listAllUser() {
        List<User> list = userMapper.listAllUser();
        if (null == list || list.isEmpty()) {
            throw new CommonException(ErrorCode.RESOURCE_NOT_EXIST);
        }
        return list;
    }

    @Override
    public Page<User> listUser(UserReqDTO userReqDTO) {
        if (Objects.isNull(userReqDTO)) {
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        PageReqDTO pageReqDTO = new PageReqDTO();
        BeanUtils.copyProperties(userReqDTO, pageReqDTO);
        PageHelper.startPage(userReqDTO.getPage().intValue(), userReqDTO.getSize().intValue());
        return userMapper.listUser(pageReqDTO.of(), userReqDTO);
    }

    @Override
    public List<User> listUser(List<Long> userIds) {
        return getBaseMapper().selectList(new QueryWrapper<User>().in("id", userIds));
    }
}
