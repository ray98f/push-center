package com.zte.msg.pushcenter.pccore.enums;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/8 11:05
 */
public enum SmsErrorCode {

    ERROR_PHONE_NUM(32100001, "错误的手机号码"),

    ERROR_TEMPLATE_ID(32100002, "错误的模版id"),

    NET_ERROR(32100003, "网络错误"),

    EXCEED_LIMIT(32100004, "同一号码发送次数过于频繁"),

    AUTH_FAIL(32100005, "第三方服务鉴权失败");

    private Integer code;

    private String message;

    SmsErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
