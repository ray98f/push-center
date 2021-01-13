package com.zte.msg.pushcenter.pccore.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author frp
 */
@Data
@ApiModel
public class ApplicationInfo extends BaseEntity {

    @ApiModelProperty(value = "应用id")
    private Long appId;

    @ApiModelProperty(value = "应用名称")
    private String appName;

    @ApiModelProperty(value = "目标平台")
    private String targetPlatform;

    @ApiModelProperty(value = "推送目标（App名称）")
    private String applicationName;

    @ApiModelProperty(value = "app消息标题")
    private String title;

    @ApiModelProperty(value = "app消息内容")
    private String content;

    @ApiModelProperty(value = "发送时间")
    private Timestamp transmitTime;

    @ApiModelProperty(value = "发送平台")
    private String providerName;

    @ApiModelProperty(value = "发送状态")
    private Integer result;

    @ApiModelProperty(value = "错误代码")
    private Integer failCode;

    @ApiModelProperty(value = "错误消息")
    private String failReason;
}
