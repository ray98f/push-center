package com.zte.msg.pushcenter.pccore.entity;

import lombok.Data;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/6 14:22
 */
@Data
public class SmsTemplate extends BaseEntity{

    private String content;

    private String params;

    private Integer status;

}
