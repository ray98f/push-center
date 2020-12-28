package com.zte.msg.pushcenter.service.impl;

import com.zte.msg.pushcenter.entity.User;
import com.zte.msg.pushcenter.enums.ErrorCode;
import com.zte.msg.pushcenter.exception.CommonException;
import com.zte.msg.pushcenter.mapper.UserMapper;
import com.zte.msg.pushcenter.service.UserService;
import com.zte.msg.pushcenter.utils.AesUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void selectUserInfo(User user){
        if (Objects.isNull(user)){
            log.error("登录信息传入为空");
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        User userInfo = userMapper.selectUserInfo(user.getUserName());
        if (Objects.isNull(userInfo)){
            log.error("用户不存在");
            throw new CommonException(ErrorCode.USER_NOT_EXIST);
        }else {
            log.info("用户搜索成功");
            if (!user.getPassword().equals(AesUtils.decrypt(userInfo.getPassword()))){
                log.error("密码错误");
                throw new CommonException(ErrorCode.LOGIN_PASSWORD_ERROR);
            }
        }
    }

    @Override
    public void insertUser(User user){
        if(Objects.isNull(user)){
            log.error("user传入参数为空");
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        User userInfo = userMapper.selectUserInfo(user.getUserName());
        if(!Objects.isNull(userInfo.getId())){
            log.error("数据已存在");
            throw new CommonException(ErrorCode.DATA_EXIST);
        }
        String password = AesUtils.encrypt(user.getPassword());
        user.setPassword(password);
        int result = userMapper.insertUser(user);
        if (result > 0){
            log.info("用户新增成功");
        }else {
            log.error("用户新增失败");
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }
    }
}
