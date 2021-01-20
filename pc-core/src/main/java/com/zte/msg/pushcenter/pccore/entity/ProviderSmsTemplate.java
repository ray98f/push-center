package com.zte.msg.pushcenter.pccore.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/6 14:34
 */
@Data
public class ProviderSmsTemplate extends BaseEntity {

    private String code;

    private Long providerId;

    private String sign;

    private String content;

    @TableField("is_enable")
    private Integer status;

}
