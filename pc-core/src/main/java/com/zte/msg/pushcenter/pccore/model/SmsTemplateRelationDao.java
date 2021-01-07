package com.zte.msg.pushcenter.pccore.model;

import lombok.Data;

import java.sql.Timestamp;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/7 11:48
 */
@Data
public class SmsTemplateRelationDao {
    /**
     * 模版id
     */
    private Long id;

    private Long relationId;

    private Long pTemplateId;

    private String content;

    private String params;

    private Integer sStatus;

    private String updatedBy;

    private Timestamp updatedAt;

    private Integer priority;

    private String code;

    private String sign;

    private String pContent;

    private Integer pStatus;

    private String providerName;

}
