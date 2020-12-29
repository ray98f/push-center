package com.zte.msg.pushcenter.entity;

import lombok.Data;

@Data
public class Role extends BaseEntity{
    private Integer roleId;

    private String roleName;

    private Integer status;
}
