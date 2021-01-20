package com.zte.msg.pushcenter.pccore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.zte.msg.pushcenter.pccore.dto.req.PasswordReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.UserReqDTO;
import com.zte.msg.pushcenter.pccore.entity.User;

import java.util.List;

/**
 * description:
 *
 * @author frp
 * @version 1.0
 * @date 2020/12/23 15:42
 */
public interface UserService extends IService<User> {
    void selectUserInfo(User user);

    void insertUser(User user);

    void changePwd(PasswordReqDTO passwordReqDTO);

    void resetPwd(Integer id);

    void editUser(UserReqDTO userReqDTO);

    void deleteUser(List<Integer> ids);

    List<User> listAllUser();

    PageInfo<User> listUser(UserReqDTO userReqDTO);

    List<User> listUser(List<Long> userIds);
}
