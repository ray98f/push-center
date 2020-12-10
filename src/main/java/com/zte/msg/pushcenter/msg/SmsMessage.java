package com.zte.msg.pushcenter.msg;

import lombok.Data;

import java.util.Map;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/10 11:13
 */
@Data
public class SmsMessage extends Message {

    /**
     * 手机号码
     */
    private String phoneNum;

    /**
     * 短信模版id
     */
    private String templateId;

    /**
     * 短信服务提供商
     */
    private String provider;

    /**
     * 变量列表
     */
    private Map<String, String> var;


}
