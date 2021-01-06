package com.zte.msg.pushcenter.pccore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;

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

    private String failReason;

    private Timestamp resultTime;
}
