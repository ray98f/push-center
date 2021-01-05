package com.zte.msg.pushcenter.entity;

import lombok.Data;

@Data
public class SecretKey extends BaseEntity{
    private Integer appId;

    private String appKey;

    private String appSecret;
}
