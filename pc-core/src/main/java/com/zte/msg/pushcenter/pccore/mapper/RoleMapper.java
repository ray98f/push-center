package com.zte.msg.pushcenter.pccore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.zte.msg.pushcenter.pccore.dto.req.RoleReqDTO;
import com.zte.msg.pushcenter.pccore.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author frp
 */
@Mapper
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> listAllRole();

    List<Role> listRole(RoleReqDTO roleReqDTO);

    int deleteRole(List<Integer> ids);

    int insertRole(Role role, String doName);

    int updateRole(Role role);

    String selectMenuIds(Long roleId);

    List<String> selectMenuRoleIdentify(List<String> ids);
}
