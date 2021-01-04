package com.zte.msg.pushcenter.pccore.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/31 14:32
 */
@Data
public class ProviderSmsTemplate extends BaseEntity{

    private Long providerId;

    private Long smsTemplateId;

    private Long smsConfigId;

    private String example;

    /**
     * 服务商短信模版id
     */
    private String sTemplateId;

    private String sign;

    @TableField(value = "`order`")
    private Integer order;
}
