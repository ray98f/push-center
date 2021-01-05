package com.zte.msg.pushcenter.pccore.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Sms extends BaseEntity{

    private Integer appId;

    private String appName;

    private Long phoneNum;

    private Date transmitTime;

    private String description;

    private String content;

    private String provider;

    private Integer templateId;
}
