package com.zte.msg.pushcenter.pccore.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author frp
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SendMode extends BaseEntity {

    private String modeName;
}
