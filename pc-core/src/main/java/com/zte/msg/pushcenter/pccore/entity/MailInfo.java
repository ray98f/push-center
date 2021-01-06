package com.zte.msg.pushcenter.pccore.entity;

import lombok.Data;

import java.util.Date;

@Data
public class MailInfo extends BaseEntity {

    private Integer appId;

    private String appName;

    private String receiveAddress;

    private String ccAddress;

    private String mailTitle;

    private String mailBody;

    private Date transmitTime;

    private String result;

    private String failReason;

    private Date resultTime;
}
