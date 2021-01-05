package com.zte.msg.pushcenter.pccore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zte.msg.pushcenter.pccore.dto.res.AppRoleResDTO;
import com.zte.msg.pushcenter.pccore.entity.AppRole;
import com.zte.msg.pushcenter.pccore.entity.Role;
import com.zte.msg.pushcenter.pccore.entity.Template;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AppRoleMapper extends BaseMapper<AppRoleResDTO> {

    List<AppRoleResDTO> listApp();

    AppRoleResDTO selectApp(Integer appId);

    List<Template> selectTemplate(Integer roleId);

    List<AppRole> selectAppRole(Integer appId);

    int editAppRole(List<AppRoleResDTO> appRoleResDTOList);

    List<Role> listRole();

    int deleteRole(Integer roleId);

    int updateRole(Role role);

    int insertRole(String roleName);

    Integer selectRoleId(String roleName);
}
