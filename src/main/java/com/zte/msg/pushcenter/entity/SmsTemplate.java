package com.zte.msg.pushcenter.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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

    @TableField(value = "sms_config_id")
    private Integer smsConfigId;

    @TableField(value = "template_id")
    private String templateId;

    private String example;
}
