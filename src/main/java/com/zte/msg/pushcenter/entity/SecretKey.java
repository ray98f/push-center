package com.zte.msg.pushcenter.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SecretKey {
    private Integer id;

    private String appId;

    private String appKey;

    private String appSecret;

    private String appToken;

    private Integer isFlag;

    private Date createTime;

    private Date updateTime;
}
