package com.zte.msg.pushcenter.pccore.core.pusher.msg;

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
    private Long messageId;

    /**
     * 消息创建时间
     */
    private Timestamp createTime;

    /**
     * 是否需要回调
     */
    private Boolean isCallBack;

    /**
     * 回调地址
     */
    private String callBackUrl;

    private PushMethods pushMethod;

}
