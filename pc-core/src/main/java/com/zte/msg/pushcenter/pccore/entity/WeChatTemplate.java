package com.zte.msg.pushcenter.pccore.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/14 10:34
 */
@Data
public class WeChatTemplate extends BaseEntity {

    private Long providerId;

    @TableField(value = "wechat_template_id")
    private String weChatTemplateId;

    private String title;

    private String content;

    private Integer status;

}
