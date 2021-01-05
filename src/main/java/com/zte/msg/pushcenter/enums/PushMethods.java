package com.zte.msg.pushcenter.enums;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/31 9:26
 */
public enum PushMethods {


    /**
     * 短信
     */
    SMS(1),

    /**
     * 邮件
     */
    MAIL(2),

    /**
     * App
     */
    APP(3),

    /**
     * 微信消息
     */
    WECHAT(4);

    private Integer type;

    PushMethods(Integer type) {
        this.type = type;
    }

    public Integer value() {
        return this.type;
    }

}
