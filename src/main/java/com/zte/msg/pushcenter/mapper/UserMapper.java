package com.zte.msg.pushcenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zte.msg.pushcenter.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
    User selectUserInfo(String userName);

    int insertUser(User user);
}
