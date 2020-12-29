package com.zte.msg.pushcenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zte.msg.pushcenter.dto.res.AppRoleResDTO;
import com.zte.msg.pushcenter.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AppRoleMapper extends BaseMapper<AppRoleResDTO> {

    List<AppRoleResDTO> listApp();

    AppRoleResDTO selectApp(Integer appId);

    List<Role> selectAppRole(Integer appId);

    int editAppRole(List<AppRoleResDTO> appRoleResDTOList);
}
