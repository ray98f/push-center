package com.zte.msg.pushcenter.pccore.core.pusher.base;

import com.zte.msg.pushcenter.pccore.enums.PushMethods;
import lombok.Data;

import java.sql.Timestamp;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/9 9:58
 */
@Data
public class Message {

    /**
     * messageId
     */
    private String messageId;

    private Long appId;

    /**
     * 是否需要回调
     */
    private Boolean isCallBack;

    /**
     * 回调地址
     */
    private String callBackUrl;

    /**
     * 推送方式
     */
    private PushMethods pushMethod;

    private Integer delay;

    private Long providerId;

    private String appName;

    private String providerName;

    private Timestamp transmitTime;

    private Long requestTime;

    public void setMessageId(String messageId) {
        this.messageId = messageId.replace("-", "");
    }

    public void setRequestTime(Long requestTime) {
        this.requestTime = System.currentTimeMillis();
    }
}
