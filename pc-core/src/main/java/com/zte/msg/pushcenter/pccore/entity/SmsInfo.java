package com.zte.msg.pushcenter.pccore.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/23 9:51
 */
@Data
@ApiModel
public class SmsInfo extends BaseEntity {

    @ApiModelProperty(value = "应用id")
    private Long appId;

    @ApiModelProperty(value = "应用名称")
    private String appName;

    @ApiModelProperty(value = "发送号码")
    private String phoneNum;

    @ApiModelProperty(value = "发送时间")
    private Timestamp transmitTime;

    @ApiModelProperty(value = "短信内容")
    private String content;

    @ApiModelProperty(value = "发送平台")
    private String providerName;

    @ApiModelProperty(value = "模板id")
    private Long templateId;

    @ApiModelProperty(value = "发送状态")
    private Integer result;

    @ApiModelProperty(value = "错误代码")
    private Integer failCode;

    @ApiModelProperty(value = "错误消息")
    private String failReason;

    @ApiModelProperty(value = "状态时间")
    private Timestamp resultTime;


}
