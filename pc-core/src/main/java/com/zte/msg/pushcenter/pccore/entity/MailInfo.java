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
    private Integer appId;

    @ApiModelProperty(value = "应用名称")
    private String appName;

    @ApiModelProperty(value = "收件地址")
    private String receiveAddress;

    @ApiModelProperty(value = "抄送地址")
    private String ccAddress;

    @ApiModelProperty(value = "邮件标题")
    private String mailTitle;

    @ApiModelProperty(value = "邮件内容")
    private String mailBody;

    @ApiModelProperty(value = "发送平台")
    private String providerName;

    @ApiModelProperty(value = "发送时间")
    private Timestamp transmitTime;

    @ApiModelProperty(value = "发送状态")
    private Integer result;

    @ApiModelProperty(value = "错误代码")
    private Integer failCode;

    @ApiModelProperty(value = "错误消息")
    private String failReason;
}
