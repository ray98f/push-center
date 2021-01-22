package com.zte.msg.pushcenter.pccore.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/6 14:38
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SmsTemplateRelation extends BaseEntity {

    private Long smsTemplateId;

    private Long providerTemplateId;

    private Integer priority;

}
