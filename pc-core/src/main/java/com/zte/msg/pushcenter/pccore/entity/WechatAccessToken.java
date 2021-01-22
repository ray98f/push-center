package com.zte.msg.pushcenter.pccore.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/18 10:17
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WechatAccessToken extends BaseEntity {

    private String appId;

    private Long expire;

    private String accessToken;
}
