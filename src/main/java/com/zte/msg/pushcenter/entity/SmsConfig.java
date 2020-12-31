package com.zte.msg.pushcenter.entity;

import lombok.Data;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/21 10:35
 */
@Data
public class SmsConfig extends BaseEntity {

    private Long providerId;

    private String name;

    private String description;

    private String sAppId;

    private String secretId;

    private String secretKey;

}
