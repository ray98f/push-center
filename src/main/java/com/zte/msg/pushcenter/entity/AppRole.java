package com.zte.msg.pushcenter.entity;

import lombok.Data;

@Data
public class AppRole extends Role {

    private Integer roleId;

    private Integer appId;

    private Integer status;
}
