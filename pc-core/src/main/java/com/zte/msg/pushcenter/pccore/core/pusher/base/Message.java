package com.zte.msg.pushcenter.pccore.core.pusher.base;

import com.zte.msg.pushcenter.pccore.enums.PushMethods;
import lombok.Data;

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
    private Long messageId;

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

}
