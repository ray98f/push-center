package com.zte.msg.pushcenter.pccore.entity;

import lombok.Data;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/24 16:29
 */
@Data
public class Script extends BaseEntity {

    private Long providerId;

    private String context;

    private Integer related;

    private Integer type;

    private String description;

    private String scriptName;

    private String scriptTag;
}
