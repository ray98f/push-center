package com.zte.msg.pushcenter.pccore.entity;

import lombok.Data;

import java.util.List;

@Data
public class AppRole extends Role {

    private Integer roleId;

    private Integer appId;

    private List<Template> template;


}
