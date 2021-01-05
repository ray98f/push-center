package com.zte.msg.pushcenter.dto.res;

import com.zte.msg.pushcenter.entity.AppRole;
import lombok.Data;

import java.util.List;

@Data
public class AppRoleResDTO {
    private Integer appId;

    private String appName;

    private List<AppRole> role;
}
