package com.zte.msg.pushcenter.dto.res;

import com.zte.msg.pushcenter.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class AppRoleResDTO {
    private Integer appId;

    private String appName;

    private List<Role> role;
}
