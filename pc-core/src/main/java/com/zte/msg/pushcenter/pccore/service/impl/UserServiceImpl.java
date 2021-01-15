package com.zte.msg.pushcenter.pccore.service.impl;

import com.zte.msg.pushcenter.pccore.dto.req.PasswordReqDTO;
import com.zte.msg.pushcenter.pccore.entity.User;
import com.zte.msg.pushcenter.pccore.enums.ErrorCode;
import com.zte.msg.pushcenter.pccore.exception.CommonException;
import com.zte.msg.pushcenter.pccore.mapper.UserMapper;
import com.zte.msg.pushcenter.pccore.service.UserService;
import com.zte.msg.pushcenter.pccore.utils.AesUtils;
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
 * @date 2020/12/28 15:42
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void selectUserInfo(User user) {
        if (Objects.isNull(user)) {
            log.error("登录信息传入为空");
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        User userInfo = userMapper.selectUserInfo(user.getUserName());
        if (Objects.isNull(userInfo)) {
            log.error("用户不存在");
            throw new CommonException(ErrorCode.USER_NOT_EXIST);
        } else {
            log.info("用户搜索成功");
            if (!user.getPassword().equals(AesUtils.decrypt(userInfo.getPassword()))) {
                log.error("密码错误");
                throw new CommonException(ErrorCode.LOGIN_PASSWORD_ERROR);
            }
        }
    }

    @Override
    public void insertUser(User user) {
        if (Objects.isNull(user)) {
            log.error("user传入参数为空");
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        User userInfo = userMapper.selectUserInfo(user.getUserName());
        if (!Objects.isNull(userInfo) && !Objects.isNull(userInfo.getId())) {
            log.error("数据已存在");
            throw new CommonException(ErrorCode.DATA_EXIST);
        }
        String password = AesUtils.encrypt(user.getPassword());
        user.setPassword(password);
        int result = userMapper.insertUser(user);
        if (result > 0) {
            log.info("用户新增成功");
        } else {
            log.error("用户新增失败");
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }
    }

    @Override
    public void changePwd(PasswordReqDTO passwordReqDTO) {
        if (Objects.isNull(passwordReqDTO)) {
            log.error("修改密码传入参数为空");
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        passwordReqDTO.setOldPwd(AesUtils.encrypt(passwordReqDTO.getOldPwd()));
        passwordReqDTO.setNewPwd(AesUtils.encrypt(passwordReqDTO.getNewPwd()));
        int result = userMapper.changePwd(passwordReqDTO);
        if (result > 0) {
            log.info("用户密码修改成功");
        } else {
            log.error("用户密码修改失败");
            throw new CommonException(ErrorCode.USER_PWD_CHANGE_FAIL);
        }
    }

    @Override
    public void deleteUser(String userName) {
        if (StringUtils.isBlank(userName)) {
            log.error("传入用户名为空");
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        int result = userMapper.deleteUser(userName);
        if (result > 0) {
            log.info("用户删除成功");
        } else {
            log.error("用户删除失败");
            throw new CommonException(ErrorCode.DELETE_ERROR);
        }
    }

    @Override
    public List<User> listUser() {
        List<User> list = userMapper.listUser();
        if (null == list || list.isEmpty()) {
            throw new CommonException(ErrorCode.RESOURCE_NOT_EXIST);
        }
        return list;
    }
}
