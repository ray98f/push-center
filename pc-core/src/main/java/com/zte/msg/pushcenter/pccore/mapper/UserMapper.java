package com.zte.msg.pushcenter.pccore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zte.msg.pushcenter.pccore.dto.req.PasswordReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.UserReqDTO;
import com.zte.msg.pushcenter.pccore.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author frp
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
    User selectUserInfo(String userName);

    List<Long> selectUserRoles(String userName);

    int insertUser(User user, String doName);

    int changePwd(PasswordReqDTO passwordReqDTO, String updateBy);

    int resetPwd(String password, String updateBy, Integer id);

    int editUser(UserReqDTO userReqDTO, String updateBy);

    int deleteUser(List<Integer> ids);

    List<User> listAllUser();

    List<User> listUser(UserReqDTO userReqDTO);
}
