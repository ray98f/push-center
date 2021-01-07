package com.zte.msg.pushcenter.pccore.entity;

import lombok.Data;

@Data
public class App extends BaseEntity{

    private String appName;

    private String appKey;

    private String appSecret;

    private Integer status;
}
