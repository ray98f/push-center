package com.zte.msg.pushcenter.pccore.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    /**
     * 获取用户信息
     * @param user
     * @return
     */
    User selectUserInfo(User user);

    /**
     * 新增用户
     * @param user
     */
    void insertUser(User user);

    /**
     * 修改密码
     * @param passwordReqDTO
     */
    void changePwd(PasswordReqDTO passwordReqDTO);

    /**
     * 重置密码
     * @param id
     */
    void resetPwd(Integer id);

    /**
     * 编辑用户
     * @param userReqDTO
     */
    void editUser(UserReqDTO userReqDTO);

    /**
     * 删除用户
     * @param ids
     */
    void deleteUser(List<Integer> ids);

    /**
     * 获取所有用户列表
     * @return
     */
    List<User> listAllUser();

    /**
     * 查询用户列表
     * @param userReqDTO
     * @return
     */
    Page<User> listUser(UserReqDTO userReqDTO);

    /**
     * 根据用户id获取用户列表
     * @param userIds
     * @return
     */
    List<User> listUser(List<Long> userIds);
}
