package com.zte.msg.pushcenter.enums;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/10 15:56
 */
public enum ErrorCode {

    /**
     * 资源配置
     */
    RESOURCE_INIT_ERROR(300001, "Resource configuration initialization failed","资源配置初始化失败"),

    /**
     * 用户
     */
    RESOURCE_AUTH_FAIL(301001, "The user has no permission to operate related resources", "该用户无相关资源操作权限"),


    /**
     * 枚举
     */
    ENUM_VALUE_ERROR(302001, "enum.value.error","枚举值错误"),
    NOT_IN_ENUM(302002, "not.in.enum","参数不在枚举范围中"),

    /**
     * 供应商
     */
    PROVIDER_EXIST(303001, "Provider is exists", "供应商已存在"),
    PROVIDER_NOT_EXIST(303002, "Provider is not exists","供应商不存在"),


    /**
     * 模板
     */
    TEMPLATE_EXIST(304001, "Template is exists","模板已存在"),
    TEMPLATE_NOT_EXIST(304002, "Template is not exists","模板不存在"),


    /**
     * 短信
     */
    SMS_PUSH_ERROR(305001, "Sms push error","短信推送错误"),

    /**
     * 邮件
     */
    MAIL_PUSH_ERROR(306001, "Mail push error","邮件推送错误"),
    MAIL_PARAM_EMPTY(306002,"Mail push parameter is empty","邮箱推送参数为空"),

    /**
     * 微信
     */
    WECHAT_PUSH_ERROR(307001, "Wechat push error","微信推送错误")
    ;


    private Integer code;

    private String enMessage;

    private String cnMessage;

    ErrorCode(Integer code, String enMessage, String cnMessage) {
        this.code = code;
        this.enMessage = enMessage;
        this.cnMessage = cnMessage;
    }

    public static String messageOf(Integer code) {
        for (ErrorCode errorCode : ErrorCode.values()) {
            if (errorCode.code.equals(code)) {
                return errorCode.cnMessage;
            }
        }
        return "";
    }

    public Integer code() {
        return this.code;
    }

    public String enMessage() {
        return this.enMessage;
    }

    public String cnMessage() {
        return this.cnMessage;
    }
}
