package com.zte.msg.pushcenter.pccore.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/14 10:34
 */
@Data
@TableName("wechat_template")
public class WeChatTemplate extends BaseEntity {

    private Long providerId;

    private String providerName;

    @TableField(value = "wechat_template_id")
    private String weChatTemplateId;

    private String title;

    private String content;

    private Integer status;

}
