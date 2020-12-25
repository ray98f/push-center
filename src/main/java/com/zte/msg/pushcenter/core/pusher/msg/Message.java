package com.zte.msg.pushcenter.core.pusher.msg;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/9 9:58
 */
@Data
public abstract class Message {

    /**
     * messageId
     */
    private Long messageId;

    /**
     * 消息内容
     */
    private String content;

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


    /**
     * 推送
     *
     * @return
     */
    public abstract JSONObject push();

    /**
     * 持久化
     */
    @Transactional(rollbackFor = Exception.class)
    public abstract void persistence();

}
