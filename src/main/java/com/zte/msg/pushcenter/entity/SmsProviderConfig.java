package com.zte.msg.pushcenter.entity;

import lombok.Data;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/21 10:35
 */
@Data
public class SmsProviderConfig extends BaseEntity {

    private Long providerId;

    private String mReq;

    private String baseUrl;

    /**
     * 接口电话号码字段名
     */
    private String pPhoneNum;

    /**
     * 接口模版id字段名
     */
    private String pTemId;

    /**
     * 接口模版值字段名
     */
    private String pTemValue;

    /**
     * 接口扩展字段，Json字符串
     */
    private String pExt;

    /**
     * 接口签名加密方法
     */
    private String mAuth;

}
