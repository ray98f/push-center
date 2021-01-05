package com.zte.msg.pushcenter.entity;

import lombok.Data;

import java.sql.Timestamp;

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

    private String  phoneNum;

    private Timestamp transmitTime;

    private String description;

    private String content;

    private String provider;

    private String templateId;
}
