package com.zte.msg.pushcenter.pccore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/23 9:51
 */
@Data
public class SmsInfo extends BaseEntity {

    private Integer appId;

    private String appName;

    private String phoneNum;

    @DateTimeFormat(pattern = "yyyy-MM-DD HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-DD HH:mm:ss")
    private Date transmitTime;

    private String content;

    private String providerName;

    private String templateId;

    private String result;

    private String failReason;

    @DateTimeFormat(pattern = "yyyy-MM-DD HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-DD HH:mm:ss")
    private Date resultTime;
}
