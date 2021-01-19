package com.zte.msg.pushcenter.pccore.enums;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/10 15:56
 */
public enum ErrorCode {

    /**
     * 鉴权
     */
    AUTHORIZATION_CHECK_FAIL(401, "authorization.check.fail"),

    AUTHORIZATION_IS_OVERDUE(401, "authorization.is.overdue"),

    AUTHORIZATION_INVALID(401, "authorization.invalid"),

    AUTHORIZATION_EMPTY(401, "authorization.empty"),
    /**
     * 该用户无相关资源操作权限
     */
    RESOURCE_AUTH_FAIL(32000001, "resource.authority.error"),

    /**
     * 参数错误
     */
    PARAM_ERROR(32000002, "param.error"),

    /**
     * 参数超过范围
     */
    PARAM_OUT_OF_RANGE(32000003, "param.range.error"),

    /**
     * 错误的枚举值
     */
    ENUM_VALUE_ERROR(32000004, "enum.value.error"),

    /**
     * 字段不符合要求，仅限中英文字母、数字、中划线和下划线，且长度在4-32之间
     */
    PARAM_PATTERN_INCOMPATIBLE(32000005, "param.pattern.incompatible"),

    /**
     * 参数不能为空
     */
    PARAM_NULL_ERROR(32000006, "param.null.error"),

    /**
     * 资源配置初始化失败
     */
    RESOURCE_INIT_ERROR(32000007, "resource.init.error"),

    /**
     * 参数小于最小值
     */
    PARAM_MIN_ERROR(32000008, "param.min"),

    DATA_EXIST(32000009, "data.exist"),

    INSERT_ERROR(31000001, "insert.error"),

    SELECT_ERROR(31000002, "select.error"),

    SELECT_EMPTY(31000002, "select.empty"),

    UPDATE_ERROR(31000003, "update.error"),

    DELETE_ERROR(31000004, "delete.error"),

    /**
     * 参数不在枚举范围中
     */
    NOT_IN_ENUM(32000010, "not.in.enum"),

    /**
     * 资源不存在
     */
    RESOURCE_NOT_EXIST(32000012, "resource.not.exist"),

    /**
     * 指定的推送方式不存在
     */
    PUSH_TYPE_NOT_EXIST(32000013, "push.type.not.exist"),

    SMS_PUSH_ERROR(32000015, "sms.push.error"),

    MAIL_PUSH_ERROR(32000016, "mail.push.error"),

    WECHAT_PUSH_ERROR(32000017, "wechat.push.error"),

    TEMPLATE_EXIST(32000019, "template.exist"),

    /**
     * 短信 32000020 - 32000029
     */
    SMS_CONFIG_NOT_EXIST(32000021, "sms.config.not.exist"),
    SMS_PROVIDER_CONFIG_NOT_NULL(32000022, "sms.provider.config.not.null"),
    SMS_CONFIG_NAME_EXIST(32000023, "sms.config.name.exist"),
    SMS_TEMPLATE_NOT_EXIST(32000024, "sms.template.not.exist"),
    SMS_PROVIDER_TEMPLATE_NOT_EXIST(32000025, "sms.provider.template.not.exist"),
    SMS_TEMPLATE_RELATION_NOT_EXIST(32000026, "sms.template.relation.not.exist"),
    SMS_TEMPLATE_RELATION_ALREADY_EXIST(32000027, "sms.template.relation.already.exist"),
    SMS_TEMPLATE_RELATION_PRIORITY_EXIST(32000028, "sms.template.relation.priority.exist"),
    SMS_TEMPLATE_PARAMS_NOT_MATCH(32000028, "sms.template.params.not.match"),

    /**
     * App 32000030 - 32000039
     */

    /**
     * 微信 32000040 - 32000049
     */

    /**
     * 邮箱 32000050 - 32000059
     */
    MAIL_PARAM_EMPTY(32000050, "mail.param.empty"),


    /**
     * 鉴权 32000060 - 32000079
     */
    USER_NOT_EXIST(32000060, "user.not.exist"),
    LOGIN_PASSWORD_ERROR(32000061, "login.password.error"),
    USER_PWD_CHANGE_FAIL(32000062, "user.pwd.change.fail"),
    APP_ROLE_EDIT_ERROR(32000063, "app.role.edit.error"),

    /**
     * 第三方供应商服务 32000080 - 32000089
     */
    PROVIDER_ALREADY_EXIST(32000080, "provider.already.exist"),
    PROVIDER_NOT_EXIST(32000081, "provider.not.exist"),
    PROVIDER_TYPE_NOT_CORRECT(32000082, "provider.type.not.correct"),
    /**
     * 脚本 32000090- 32000099
     */
    SCRIPT_TYPE_ERROR(32000090, "script.type.error"),
    SCRIPT_NOT_EXIST(32000091, "script.not.exist"),

    /**
     * OpenApi签名校验
     */
    OPENAPI_VERIFY_FAIL(32000100, "openapi.verify.fail"),

    /**
     * 字典
     */
    DIC_TYPE_ALREADY_EXIST(32000121, "dic.type.already.exist"),
    DIC_TYPE_NOT_EXIST(32000122, "dic.type.not.exist"),
    /**
     * 其他
     */
    SECRET_RESET_ERROR(32100000, "secret.reset.error"),
    FILE_DELETE_ERROR(32100001, "file.delete.error");

    private Integer code;

    private String message;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String messageOf(Integer code) {
        for (ErrorCode errorCode : ErrorCode.values()) {
            if (errorCode.code.equals(code)) {
                return errorCode.message;
            }
        }
        return "";
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

}
