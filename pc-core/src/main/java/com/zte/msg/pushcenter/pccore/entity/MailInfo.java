package com.zte.msg.pushcenter.pccore.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
@ApiModel
public class MailInfo extends BaseEntity {

    @ApiModelProperty(value = "应用id")
    @JsonProperty(value = "app_id")
    private Integer appId;

    @ApiModelProperty(value = "应用名称")
    @JsonProperty(value = "app_name")
    private String appName;

    @ApiModelProperty(value = "收件地址")
    @JsonProperty(value = "receive_address")
    private String receiveAddress;

    @ApiModelProperty(value = "抄送地址")
    @JsonProperty(value = "cc_address")
    private String ccAddress;

    @ApiModelProperty(value = "邮件标题")
    @JsonProperty(value = "mail_title")
    private String mailTitle;

    @ApiModelProperty(value = "邮件内容")
    @JsonProperty(value = "mail_body")
    private String mailBody;

    @ApiModelProperty(value = "发送平台")
    @JsonProperty(value = "provider_name")
    private String providerName;

    @ApiModelProperty(value = "发送时间")
    @JsonProperty(value = "transmit_time")
    private Timestamp transmitTime;

    @ApiModelProperty(value = "发送状态")
    private Integer result;

    @ApiModelProperty(value = "错误代码")
    @JsonProperty(value = "fail_code")
    private Integer failCode;

    @ApiModelProperty(value = "错误消息")
    @JsonProperty(value = "fail_reason")
    private String failReason;
}
