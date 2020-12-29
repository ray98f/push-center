package com.zte.msg.pushcenter.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/21 13:54
 */
@Data
@TableName(value = "sms_template")
public class SmsTemplate extends BaseEntity {

    private Integer smsConfigId;

    private String templateId;

    private String example;

    private String sign;
}
