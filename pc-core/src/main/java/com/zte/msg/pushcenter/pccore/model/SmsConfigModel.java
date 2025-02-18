package com.zte.msg.pushcenter.pccore.model;

import lombok.Data;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/11 9:39
 */
@Data
public class SmsConfigModel {

    private long smsTemplateId;

    private long providerTemplateId;

    private int priority;

    private int templateStatus;

    private String code;

    private String sign;

    private String content;

    private int platformTemplateStatus;

    private String providerName;

    private String scriptTag;

    private String config;
}
