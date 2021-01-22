package com.zte.msg.pushcenter.pccore.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author frp
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SecretKey extends BaseEntity{
    private Integer appId;

    private String appKey;

    private String appSecret;
}
