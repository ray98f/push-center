package com.zte.msg.pushcenter.pccore.service;

import com.zte.msg.pushcenter.pccore.dto.req.PasswordReqDTO;
import com.zte.msg.pushcenter.pccore.entity.User;

/**
 * description:
 *
 * @author frp
 * @version 1.0
 * @date 2020/12/23 15:42
 */
public interface UserService {
    void selectUserInfo(User user);

    void insertUser(User user);

    void changePwd(PasswordReqDTO passwordReqDTO);

    void deleteUser(String userName);
}
