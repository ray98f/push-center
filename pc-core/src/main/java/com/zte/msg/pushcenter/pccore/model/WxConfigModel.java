package com.zte.msg.pushcenter.pccore.model;

import lombok.Data;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/14 16:23
 */
@Data
public class WxConfigModel {

    private Long templateId;

    private Long providerId;

    private String providerName;

    private String wechatTemplateId;

    private String title;

    private String content;

    private String scriptTag;

    private String scriptContext;

    private String config;

}
