package com.zte.msg.pushcenter.pccore.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class MailInfo extends BaseEntity {

    private Integer appId;

    private String appName;

    private String receiveAddress;

    private String ccAddress;

    private String mailTitle;

    private String mailBody;

    private Timestamp transmitTime;

    private String result;

    private Integer failCode;

    private String failReason;
}
