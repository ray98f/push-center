package com.zte.msg.pushcenter.pccore.entity;

import lombok.Data;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/6 14:34
 */
@Data
public class ProviderSmsTemplate extends BaseEntity {

    private String code;

    private Long providerId;

    private String sign;

    private String content;

    private Integer status;

}
